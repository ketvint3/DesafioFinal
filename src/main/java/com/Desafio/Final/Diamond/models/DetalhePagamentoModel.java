package com.Desafio.Final.Diamond.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity(name = "tb_detalhe_pagamento")
public class DetalhePagamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;

    @Column(name = "valor_empresa")
    private BigDecimal valorEmpresa;
    @Column(name = "valor_motorista")
    private BigDecimal valorMotorista;

}
