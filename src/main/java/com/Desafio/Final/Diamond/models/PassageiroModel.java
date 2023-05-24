package com.Desafio.Final.Diamond.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Entity(name = "tb_passageiro")
public class PassageiroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private Integer codigo;
    @NotNull
    @Size(min = 4,max = 50,message = "Nome deve conter no minimo 4 caracteres")
    private String nome;
    @Email
    @NotNull
    private String email;
    @NotNull
    @CPF
    private String CPF;
    @NotNull
    private String senha;
}
