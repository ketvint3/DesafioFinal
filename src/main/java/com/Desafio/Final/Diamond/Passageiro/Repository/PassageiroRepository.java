package com.Desafio.Final.Diamond.Passageiro.Repository;

import java.util.ArrayList;
import java.util.List;

import com.Desafio.Final.Diamond.Passageiro.Models.Passageiro;
import org.springframework.stereotype.Repository;

@Repository
public class PassageiroRepository {
    private List<Passageiro> passageiros;

    public PassageiroRepository() {
        passageiros = new ArrayList<>();
    }

    public List<Passageiro> listarPassageiros() {
        return passageiros;
    }

    public Passageiro buscarPassageiro(String id) {
        for (Passageiro passageiro : passageiros) {
            if (passageiro.getId().equals(id)) {
                return passageiro;
            }
        }
        return null;
    }

    public void adicionarPassageiro(Passageiro passageiro) {
        passageiros.add(passageiro);
    }

    public void atualizarPassageiro(Passageiro passageiroAtualizado) {
        Passageiro passageiroExistente = buscarPassageiro(passageiroAtualizado.getId());
        if (passageiroExistente != null) {
            passageiroExistente.setNome(passageiroAtualizado.getNome());
            passageiroExistente.setEndereco(passageiroAtualizado.getEndereco());
        }
    }

    public void removerPassageiro(String id) {
        Passageiro passageiro = buscarPassageiro(id);
        if (passageiro != null) {
            passageiros.remove(passageiro);
        }
    }
}

