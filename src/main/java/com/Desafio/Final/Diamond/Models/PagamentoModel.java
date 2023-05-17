package com.Desafio.Final.Diamond.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class PagamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String taxa;

    private String valor;

    @ManyToOne
    private LocalizacaoModel localizacao;

}

