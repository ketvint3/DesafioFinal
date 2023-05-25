package com.Desafio.Final.Diamond.services;

import com.Desafio.Final.Diamond.models.MotoristaModel;
import com.Desafio.Final.Diamond.models.ViagemModel;
import com.Desafio.Final.Diamond.repositories.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoristaService {

    @Autowired
    private MotoristaRepository repository;

    public void adicionar(MotoristaModel novoMotorista) {
        repository.save(novoMotorista);
    }

    public List<MotoristaModel> listar() {
        return repository.findAll();
    }
    public MotoristaModel buscarCodigo(Integer codigo) {

        Optional<MotoristaModel> optMotorista = repository.findById(codigo);
        if (optMotorista.isEmpty()) {

            return null;
        }
        return optMotorista.get();
    }

    public void update(Integer codigo, MotoristaModel motorista) {
        if (repository.existsById(codigo)) {
            repository.save(motorista);
        }
    }

    public void remover(Integer codigo) {
        if (repository.existsById(codigo)) {
            repository.deleteById(codigo);
        }
    }
    public MotoristaModel buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }
    public String gerarNovaSenha() {
        int length = 8;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder newPassword = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * characters.length());
            newPassword.append(characters.charAt(randomIndex));
        }

        return newPassword.toString();
    }
}
