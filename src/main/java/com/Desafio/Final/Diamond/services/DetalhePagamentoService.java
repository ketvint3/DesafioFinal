package com.Desafio.Final.Diamond.services;

import com.Desafio.Final.Diamond.models.DetalhePagamentoModel;
import com.Desafio.Final.Diamond.models.PagamentoModel;
import com.Desafio.Final.Diamond.repositories.DetalhePagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalhePagamentoService {
    @Autowired
    private DetalhePagamentoRepository detalhePagamentoRepository;

    public void adicionar(DetalhePagamentoModel detalhePagamento) {

        detalhePagamentoRepository.save(detalhePagamento);

    }
    public List<DetalhePagamentoModel> listar() {

        return detalhePagamentoRepository.findAll();
    }

    public DetalhePagamentoModel buscarCodigo(Integer codigo) {

        Optional<DetalhePagamentoModel> optValor = detalhePagamentoRepository.findById(codigo);
        if (optValor.isEmpty()) {

            return null;
        }
        return optValor.get();
    }

    public void alterar(Integer codigo, DetalhePagamentoModel detalhePagamento) {


        if (detalhePagamentoRepository.existsById(codigo)) {
            detalhePagamentoRepository.save(detalhePagamento);

        }
    }
    public void deletar(Integer codigo) {

        if (detalhePagamentoRepository.existsById(codigo)) {
            detalhePagamentoRepository.deleteById(codigo);
        }
    }
}
