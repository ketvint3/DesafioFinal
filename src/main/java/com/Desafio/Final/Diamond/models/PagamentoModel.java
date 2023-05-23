package com.Desafio.Final.Diamond.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity(name = "tb_pagamento")
public class PagamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;

    @Column(name = "km_rodado")
    private Double kmRodado;

    @Column(name = "valor_final")
    private  BigDecimal valorFinal;

    @ManyToOne
    @JoinColumn(name = "valor_id")
    private ValorModel valor;


    // O pagamento vai ser de acordo com o km
    // pagamento = pagamento base  da viagem + Km a ser rodado * taxa do Km rodado
}
