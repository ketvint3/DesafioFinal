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

    @Column(name = "taxa_por_km")
    private Double taxaPorKm;

    @Column(name = "pagamento_base")
    private BigDecimal pagamentoBase;

    @Column(name = "km_rodado")
    private Double kmRodado;

    @Column(name = "valor_final")
    private  BigDecimal valorFinal;


    // O pagamento vai ser de acordo com o km
    // pagamento = pagamento base  da viagem+ Km a ser rodado * taxa do Km rodado
}
