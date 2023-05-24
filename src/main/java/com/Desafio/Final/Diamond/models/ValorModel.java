package com.Desafio.Final.Diamond.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "tb_valor")
public class ValorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private Integer codigo;

    @Column(name = "taxa_por_km")
    @NotNull
    private Double taxaPorKm;

    @Column(name = "valor_base")
    @NotNull
    private Double valorBase;

}
