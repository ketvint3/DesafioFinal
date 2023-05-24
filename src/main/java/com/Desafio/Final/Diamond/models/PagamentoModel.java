package com.Desafio.Final.Diamond.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity(name = "tb_pagamento")
public class PagamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private Integer codigo;

    @Column(name = "taxa_por_km")
    private Double taxaPorKm;

    @NotNull
    @Column(name = "pagamento_base")
    private BigDecimal pagamentoBase;

    @NotNull
    @Column(name = "km_rodado")
    private Double kmRodado;
    @NotNull
    @Column(name = "valor_final")
    private BigDecimal valorFinal;


    // O pagamento vai ser de acordo com o km
    // pagamento = pagamento base  da viagem+ Km a ser rodado * taxa do Km rodado
}
