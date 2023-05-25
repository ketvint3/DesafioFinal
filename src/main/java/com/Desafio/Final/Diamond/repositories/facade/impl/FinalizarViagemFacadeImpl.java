package com.Desafio.Final.Diamond.repositories.facade.impl;

import com.Desafio.Final.Diamond.models.PagamentoModel;
import com.Desafio.Final.Diamond.models.ViagemModel;
import com.Desafio.Final.Diamond.models.enu.ViagemEnum;
import com.Desafio.Final.Diamond.repositories.facade.FinalizarViagemFacade;
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

    @Override
    public boolean finalizarViagem(Integer codigoViagem, PagamentoModel pagamento, Integer codigoValor) {

        pagamento.setValor(valorService.buscarCodigo(codigoValor));
        ViagemModel viagemModel = viagemService.buscarCodigo(codigoViagem);
        Double valorFinal = viagemService.calcularPagamento(pagamento, viagemModel.getLatitudePartida(), viagemModel.getLongitudePartida(),
                viagemModel.getLatitudeChegada(), viagemModel.getLongitudeChegada());
        pagamento.setValorFinal(BigDecimal.valueOf(valorFinal));

        pagamentoService.savePagamento(pagamento);



        if (viagemModel != null) {
            viagemModel.setStatusViagem(ViagemEnum.FINALIZADO);
            viagemService.update(codigoViagem, viagemModel);

            return true;
        }
        return false;
    }
}
