package com.Desafio.Final.Diamond.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Data
    @Entity(name = "tb_localizaçao")
    public class LocalizacaoModel {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private  Integer codigo;
        private BigDecimal longitude;
        private BigDecimal latitude;


        //ENTIDADE
        private  String viagem;


    }

