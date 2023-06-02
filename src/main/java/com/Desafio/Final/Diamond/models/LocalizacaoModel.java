package com.Desafio.Final.Diamond.models;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity(name = "tb_localizacao")
public class LocalizacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    private String longitude;
    private String latitude;

}
