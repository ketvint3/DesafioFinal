package com.Desafio.Final.Diamond.services;

import com.Desafio.Final.Diamond.models.ValorModel;
import com.Desafio.Final.Diamond.repositories.ValorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValorService {

    @Autowired
private ValorRepository valorRepository;

    public void adicionar(ValorModel valor) {

       valorRepository.save(valor);

    }
    public List<ValorModel> listar() {

        return valorRepository.findAll();
    }

    public ValorModel buscarCodigo(Integer codigo) {

        Optional<ValorModel> optValor = valorRepository.findById(codigo);
        if (optValor.isEmpty()) {

            return null;
        }
        return optValor.get();
    }

    public void alterar(Integer codigo, ValorModel valor) {


     if (valorRepository.existsById(codigo)) {
         valorRepository.save(valor);

     }
    }
    public void deletar(Integer codigo) {

        if (valorRepository.existsById(codigo)) {
            valorRepository.deleteById(codigo);
        }
    }
}
