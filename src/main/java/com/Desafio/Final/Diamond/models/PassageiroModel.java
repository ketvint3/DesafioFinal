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
    private Integer codigo;
    @NotNull
    @Size(min = 3,max = 50,message =
            "Nome deve conter no minimo 3 caracteres")
    private String nome;
    @Email
    @NotNull
    private String email;
    @CPF
    @NotNull
    private String CPF;
    @NotNull
    private String senha;

    @Column(nullable = true, length = 64)
    private String photos;


    @Transient
    public String getPhotosImagePath() {
        if (photos == null || codigo == null) return null;

        return "/Passageiro-photos/" + codigo + "/" + photos;
    }
}
