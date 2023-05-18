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

    public PassageiroModel buscarCodigo(Integer id) {
        Optional<PassageiroModel> optPassagem = passageiroRepository.findById(id);

        if (optPassagem.isEmpty()) {
            return null;
        }
        return optPassagem.get();
    }

    public void adicionarPassageiro(PassageiroModel passageiro) {

        passageiroRepository.save(passageiro);
    }

    public void atualizarPassageiro(Integer id, PassageiroModel passageiroAtualizado) {
        if (passageiroRepository.existsById(id)) {
            passageiroRepository.save(passageiroAtualizado);
        }
    }

    public void removerPassageiro(Integer id) {
        if (passageiroRepository.existsById(id)) {
            passageiroRepository.deleteById(id);
        }
    }
    public PassageiroModel buscarPassageiro(Integer id) {
        Optional<PassageiroModel> optPassagem = passageiroRepository.findById(id);

        if (optPassagem.isEmpty() || "recusada".equals(optPassagem.get().getRecusada())) {
            return null;
        }
        return optPassagem.get();
    }
    public void recusarViagem(Integer id) {
        Optional<PassageiroModel> optPassagem = passageiroRepository.findById(id);

        if (optPassagem.isPresent()) {
            PassageiroModel passageiro = optPassagem.get();
            passageiro.setRecusada("recusada a taxa foi cobrada."); // Definindo o valor "recusada" para o campo recusada
            passageiroRepository.save(passageiro);
        }
    }

}
