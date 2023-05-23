package com.Desafio.Final.Diamond.services;

import com.Desafio.Final.Diamond.models.PagamentoModel;
import com.Desafio.Final.Diamond.models.ValorModel;
import com.Desafio.Final.Diamond.models.ViagemModel;
import com.Desafio.Final.Diamond.repositories.PagamentoRepository;
import com.Desafio.Final.Diamond.repositories.ValorRepository;
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

    public List<ViagemModel> listarPendentes () {

        return viagemRepositoryCustom.listarPendentes();
    }

    public Double calcularPagamento(Integer codigo) {

        Optional<PagamentoModel> pagamentoModel = pagamentoRepository.findById(codigo);
        if (pagamentoModel.isEmpty()) {

            return null;
        }
        PagamentoModel pagamento = pagamentoModel.get();

        double valorFinal = pagamento.getValor().getValorBase() + pagamento.getKmRodado() * pagamento.getValor().getTaxaPorKm();

        pagamento.setValorFinal(BigDecimal.valueOf(valorFinal));
        return valorFinal;
    }
}
