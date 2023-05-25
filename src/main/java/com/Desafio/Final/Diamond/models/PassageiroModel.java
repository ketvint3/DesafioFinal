package com.Desafio.Final.Diamond.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Data
@Entity(name = "tb_passageiro")
public class PassageiroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;
    private String nome;
    @Email
    private String email;

    @CPF
    private String CPF;
    private String senha;

    @Column(nullable = true, length = 64)
    private String photos;



    @Transient
    public String getPhotosImagePath(){
        if (photos == null || codigo == null) return null;

        return "/Passageiro-photos/"+ codigo + "/" + photos;
    }

}

