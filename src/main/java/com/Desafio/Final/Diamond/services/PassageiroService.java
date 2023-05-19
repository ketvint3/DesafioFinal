package com.Desafio.Final.Diamond.services;

import com.Desafio.Final.Diamond.models.PassageiroModel;
import com.Desafio.Final.Diamond.repositories.PassageiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassageiroService {

    @Autowired
    private PassageiroRepository passageiroRepository;

    public PassageiroService(PassageiroRepository passageiroRepository) {
        this.passageiroRepository = passageiroRepository;
    }

    public List<PassageiroModel> listarPassageiros() {
        return passageiroRepository.findAll();
    }

    public PassageiroModel buscarCodigo(Integer codigo) {
        Optional<PassageiroModel> optPassagem = passageiroRepository.findById(codigo);

        if (optPassagem.isEmpty()) {
            return null;
        }
        return optPassagem.get();
    }

    public void adicionarPassageiro(PassageiroModel passageiro) {

        passageiroRepository.save(passageiro);
    }

    public void atualizarPassageiro(Integer codigo, PassageiroModel passageiroAtualizado) {
        if (passageiroRepository.existsById(codigo)) {
            passageiroRepository.save(passageiroAtualizado);
        }
    }

    public void removerPassageiro(Integer codigo) {
        if (passageiroRepository.existsById(codigo)) {
            passageiroRepository.deleteById(codigo);
        }
    }
}
