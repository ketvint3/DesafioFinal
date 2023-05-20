package com.Desafio.Final.Diamond.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Entity(name = "tb_motorista")
public class MotoristaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;
    private String nome;
    @CPF
    private String cpf;
    @Email
    private String email;
    private String veiculo;

    @Column(nullable = true, length = 64)
    private String photos;

    @Transient
    public String getPhotosImagePath(){
        if (photos == null || codigo == null) return null;

        return "/motorista-photos/"+ codigo + "/" + photos;
    }
}
