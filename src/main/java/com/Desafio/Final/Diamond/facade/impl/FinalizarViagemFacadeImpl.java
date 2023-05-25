package com.Desafio.Final.Diamond.facade.impl;

import com.Desafio.Final.Diamond.facade.FinalizarViagemFacade;
import com.Desafio.Final.Diamond.models.DetalhePagamentoModel;
import com.Desafio.Final.Diamond.models.PagamentoModel;
import com.Desafio.Final.Diamond.models.ViagemModel;
import com.Desafio.Final.Diamond.models.enu.ViagemEnum;
import com.Desafio.Final.Diamond.services.DetalhePagamentoService;
import com.Desafio.Final.Diamond.services.PagamentoService;
import com.Desafio.Final.Diamond.services.ValorService;
import com.Desafio.Final.Diamond.services.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class FinalizarViagemFacadeImpl implements FinalizarViagemFacade {

    @Autowired
    private ViagemService viagemService;
    @Autowired
    private ValorService valorService;
    @Autowired
    private PagamentoService pagamentoService;
    @Autowired
    private DetalhePagamentoService detalhePagamento;
    @Override
    public boolean finalizarViagem(Integer codigoViagem, PagamentoModel pagamento, Integer codigoValor) {

        pagamento.setValor(valorService.buscarCodigo(codigoValor));

        ViagemModel viagemModel = viagemService.buscarCodigo(codigoViagem);

        Double distancia = viagemService.calcularDistancia(viagemModel.getLatitudePartida(), viagemModel.getLatitudeChegada(),
                viagemModel.getLongitudePartida(), viagemModel.getLongitudeChegada());

        Double valorFinal = viagemService.calcularPagamento(pagamento, distancia);

        pagamento.setDistancia(distancia);
        pagamento.setValorFinal(BigDecimal.valueOf(valorFinal));

        BigDecimal valorEmpresa = viagemService.calcularTaxaEmpresa(pagamento);
        DetalhePagamentoModel detalhePagamentoModel = new DetalhePagamentoModel();
        detalhePagamentoModel.setValorEmpresa(valorEmpresa);

        BigDecimal valorMotorista = viagemService.calcularTaxaMotorista(pagamento);
        detalhePagamentoModel.setValorMotorista(valorMotorista);

        detalhePagamento.adicionar(detalhePagamentoModel);

        pagamento.setDetalhePagamento(detalhePagamentoModel);
        pagamentoService.savePagamento(pagamento);

        viagemModel.setPagamento(pagamento);

        if (viagemModel != null) {
            viagemModel.setStatusViagem(ViagemEnum.FINALIZADO);
            viagemService.update(codigoViagem, viagemModel);

            return true;
        }
        return false;
    }
}