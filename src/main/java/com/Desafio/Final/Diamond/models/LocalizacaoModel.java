package com.Desafio.Final.Diamond.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Entity(name = "tb_localizacao")
public class LocalizacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer codigo;
    @NotNull
    private String longitude;
    @NotNull
    private String latitude;

}
