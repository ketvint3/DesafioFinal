package com.Desafio.Final.Diamond.services;

import com.Desafio.Final.Diamond.models.PassageiroModel;
import com.Desafio.Final.Diamond.repositories.PassageiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassageiroService {

    @Autowired
    private JavaMailSender emailSender;
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

//email]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]
public PassageiroModel buscarPorEmail(String email) {
    return passageiroRepository.findByEmail(email);
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
//email]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]

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
