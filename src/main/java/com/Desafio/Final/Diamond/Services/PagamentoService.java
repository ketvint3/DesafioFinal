package com.Desafio.Final.Diamond.Services;


import com.Desafio.Final.Diamond.Models.PagamentoModel;
import com.Desafio.Final.Diamond.Repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
 public class PagamentoService{
    @Autowired
    private PagamentoRepository pagamentoRepository;

    public PagamentoModel savePagamento(PagamentoModel pagamentoModel) {
            return pagamentoRepository.save(pagamentoModel);
    }

    public void deletePagamentoModel(Integer id) {
            pagamentoRepository.deleteById(id);
    }

    public BigDecimal calcularPagamento(Integer id, Double kmRodado) {
       PagamentoModel pagamentoModel = pagamentoRepository.findById(id).orElse(null);

    if (pagamentoModel == null) {
            return null;
    }

    Double valorRodado = kmRodado * pagamentoModel.getTaxaPorKm();
    BigDecimal valorFinal = pagamentoModel.getPagamentoBase().add(BigDecimal.valueOf(valorRodado));

    pagamentoModel.setValorRodado(valorRodado);
    pagamentoModel.setValorFinal(valorFinal);

    pagamentoRepository.save(pagamentoModel);

    return valorFinal;
    }

 }



