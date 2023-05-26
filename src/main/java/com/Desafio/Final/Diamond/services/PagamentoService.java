package com.Desafio.Final.Diamond.services;

import com.Desafio.Final.Diamond.models.PagamentoModel;
import com.Desafio.Final.Diamond.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public PagamentoModel buscarCodigo(Integer codigo) {

        Optional<PagamentoModel> optValor = pagamentoRepository.findById(codigo);
        if (optValor.isEmpty()) {

            return null;
        }
        return optValor.get();
    }

    public void deletePagamentoModel(Integer codigo) {

        pagamentoRepository.deleteById(codigo);
    }
}
