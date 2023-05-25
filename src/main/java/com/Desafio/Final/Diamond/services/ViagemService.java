package com.Desafio.Final.Diamond.services;

import com.Desafio.Final.Diamond.models.PagamentoModel;
import com.Desafio.Final.Diamond.models.ViagemModel;
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
        Optional<ViagemModel> optViagem = viagemRepository.findById(codigo);
        if (optViagem.isPresent()) {
            ViagemModel viagemExistente = optViagem.get();
            viagemExistente.setPassageiro(viagem.getPassageiro());
            viagemExistente.setPagamento(viagem.getPagamento());
            viagemExistente.setStatusViagem(viagem.getStatusViagem());
            viagemRepository.save(viagemExistente);
        }
    }

    public ViagemModel buscarCodigo(Integer codigo) {
        Optional<ViagemModel> optViagem = viagemRepository.findById(codigo);
        return optViagem.orElse(null);
    }

    public void remover(Integer codigo) {
        if (viagemRepository.existsById(codigo)) {
            viagemRepository.deleteById(codigo);
        }
    }

    public List<ViagemModel> listarPendentes() {
        return viagemRepositoryCustom.listarPendentes();
    }

    public Double calcularPagamento(PagamentoModel pagamento, Double latitudePartida, Double longitudePartida,
                                    Double latitudeChegada, Double longitudeChegada) {
        if (pagamento == null) {
            return null;
        }

        double distancia = calcularDistancia(latitudePartida, longitudePartida, latitudeChegada, longitudeChegada);
        double valorFinal = pagamento.getValor().getValorBase() + distancia * pagamento.getValor().getTaxaPorKm();

        pagamento.setValorFinal(BigDecimal.valueOf(valorFinal));
        return valorFinal;
    }

    private double calcularDistancia(Double latitudePartida, Double longitudePartida,
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
}

