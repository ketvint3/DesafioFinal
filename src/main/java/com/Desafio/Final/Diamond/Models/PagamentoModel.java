package com.Desafio.Final.Diamond.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

    @Data
    @Entity
    public class PagamentoModel {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private Double taxaPorKm;

        private BigDecimal pagamentoBase;

        private Double valorRodado;

        private  BigDecimal valorFinal;


        // O pagamento vai ser de acordo com o km
        // pagamento = pagamento base  da viagem+ Km a ser rodado * taxa do Km rodado

    }

