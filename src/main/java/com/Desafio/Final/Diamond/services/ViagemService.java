package com.Desafio.Final.Diamond.services;

import com.Desafio.Final.Diamond.models.ViagemModel;
import com.Desafio.Final.Diamond.repositories.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViagemService {
    @Autowired
    private ViagemRepository viagemRepository;

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

    public ViagemModel listarCodigo(Integer codigo) {

        Optional<ViagemModel> optViagem = viagemRepository.findById(codigo);
        if (optViagem.isEmpty()) {

            return null;
        }
        return optViagem.get();
    }

    public void remover(Integer codigo) {

        if (viagemRepository.existsById(codigo)){
            viagemRepository.deleteById(codigo);
        }
    }
}
