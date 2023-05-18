package com.Desafio.Final.Diamond.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_passageiro")
public class PassageiroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;
    private String nome;
    private String endereco;
    private String recusada;

}
