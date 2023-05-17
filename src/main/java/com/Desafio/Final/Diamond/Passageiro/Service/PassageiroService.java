package com.Desafio.Final.Diamond.Passageiro.Service;

import com.Desafio.Final.Diamond.Passageiro.Models.Passageiro;
import com.Desafio.Final.Diamond.Passageiro.Repository.PassageiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassageiroService {
    private PassageiroRepository passageiroRepository;

    @Autowired
    public PassageiroService(PassageiroRepository passageiroRepository) {
        this.passageiroRepository = passageiroRepository;
    }

    public List<Passageiro> listarPassageiros() {
        return passageiroRepository.listarPassageiros();
    }

    public Passageiro buscarPassageiro(String id) {
        return passageiroRepository.buscarPassageiro(id);
    }

    public void adicionarPassageiro(Passageiro passageiro) {
        passageiroRepository.adicionarPassageiro(passageiro);
    }

    public void atualizarPassageiro(Passageiro passageiroAtualizado) {
        passageiroRepository.atualizarPassageiro(passageiroAtualizado);
    }

    public void removerPassageiro(String id) {
        passageiroRepository.removerPassageiro(id);
    }
}

