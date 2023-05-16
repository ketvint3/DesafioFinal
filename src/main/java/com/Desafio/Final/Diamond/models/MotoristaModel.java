package com.Desafio.Final.Diamond.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_motorista")
public class MotoristaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;
    private String nome;
    private String cpf;
    private String email;
    private String veiculo;
}
