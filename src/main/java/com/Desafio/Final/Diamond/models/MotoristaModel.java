package com.Desafio.Final.Diamond.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Entity(name = "tb_motorista")
public class MotoristaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;
    @NotNull
    @Size(min = 3, max = 55, message = "" +
            "Nome deve conter no minimo 3 caracteres")
    private String nome;
    @CPF
    @NotNull
    private String cpf;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String veiculo;

    @Column(nullable = true, length = 64)
    private String photos;

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || codigo == null) return null;

        return "/motorista-photos/" + codigo + "/" + photos;
    }
}
