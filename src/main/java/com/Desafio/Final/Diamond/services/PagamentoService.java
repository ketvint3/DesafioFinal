package com.Desafio.Final.Diamond.services;

import com.Desafio.Final.Diamond.models.PagamentoModel;
import com.Desafio.Final.Diamond.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public PagamentoModel savePagamento(PagamentoModel pagamentoModel) {
        return pagamentoRepository.save(pagamentoModel);
    }

    public List<PagamentoModel> listar() {

        return pagamentoRepository.findAll();
    }

    public void deletePagamentoModel(Integer codigo) {

        pagamentoRepository.deleteById(codigo);
    }

    public BigDecimal calcularPagamento(Integer codigo, Double kmRodado) {
        PagamentoModel pagamentoModel = pagamentoRepository.findById(codigo).orElse(null);

        if (pagamentoModel == null) {
            return null;
        }

        Double valorRodado = kmRodado * pagamentoModel.getTaxaPorKm();
        BigDecimal valorFinal = pagamentoModel.getPagamentoBase().add(BigDecimal.valueOf(valorRodado));

        pagamentoModel.setKmRodado(valorRodado);
        pagamentoModel.setValorFinal(valorFinal);

        pagamentoRepository.save(pagamentoModel);

        return valorFinal;
    }
}
