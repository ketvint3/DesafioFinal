package com.Desafio.Final.Diamond.services;

import com.Desafio.Final.Diamond.models.PagamentoModel;
import com.Desafio.Final.Diamond.models.ViagemModel;
import com.Desafio.Final.Diamond.repositories.PagamentoRepository;
import com.Desafio.Final.Diamond.repositories.ViagemRepository;
import com.Desafio.Final.Diamond.repositories.criteria.ViagemRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ViagemService {
    @Autowired
    private ViagemRepository viagemRepository;
    @Autowired
    private ViagemRepositoryCustom viagemRepositoryCustom;
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public void cadastrar(ViagemModel viagem) {

        viagemRepository.save(viagem);
    }

    public List<ViagemModel> listar() {

        return viagemRepository.findAll();
    }

    public void update(Integer codigo, ViagemModel viagem) {

        if (viagemRepository.existsById(codigo)) {
            viagemRepository.save(viagem);
        }
    }

    public ViagemModel buscarCodigo(Integer codigo) {

        Optional<ViagemModel> optViagem = viagemRepository.findById(codigo);
        if (optViagem.isEmpty()) {

            return null;
        }
        return optViagem.get();
    }

    public void remover(Integer codigo) {

        if (viagemRepository.existsById(codigo)) {
            viagemRepository.deleteById(codigo);
        }
    }

    public List<ViagemModel> listarPendentes() {

        return viagemRepositoryCustom.listarPendentes();
    }

    public Double calcularPagamento(PagamentoModel pagamento, Double distancia) {

        if (pagamento == null) {

            return null;
        }

        double valorFinal = pagamento.getValor().getValorBase() + distancia * pagamento.getValor().getTaxaPorKm();

        pagamento.setValorFinal(BigDecimal.valueOf(valorFinal));
        return valorFinal;
    }

    public double calcularDistancia(Double latitudePartida, Double longitudePartida,
                                     Double latitudeChegada, Double longitudeChegada) {
        final int RAIO_TERRA = 6371;

        double dLat = Math.toRadians(latitudeChegada - latitudePartida);
        double dLon = Math.toRadians(longitudeChegada - longitudePartida);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(latitudePartida)) * Math.cos(Math.toRadians(latitudeChegada)) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distancia = RAIO_TERRA * c;
        return distancia;
    }

    public BigDecimal calcularTaxaEmpresa(PagamentoModel pagamento) {

        BigDecimal valorEmpresa = BigDecimal.valueOf(pagamento.getValorFinal().doubleValue() * 0.15);
         return valorEmpresa;
    }

    public BigDecimal calcularTaxaMotorista(PagamentoModel pagamento) {

        BigDecimal valorMotorista = BigDecimal.valueOf(pagamento.getValorFinal().doubleValue() * 0.85);
        return valorMotorista;
    }
}
