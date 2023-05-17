package com.Desafio.Final.Diamond.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity(name = "tb_localizacao")
public class LocalizacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    private BigDecimal longitude;
    private BigDecimal latitude;

}
