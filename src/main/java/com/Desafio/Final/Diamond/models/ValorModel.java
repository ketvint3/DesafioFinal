package com.Desafio.Final.Diamond.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_valor")
public class ValorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;

    @Column(name = "taxa_por_km")
    private Double taxaPorKm;

    @Column(name = "pagamento_base")
    private Double valorBase;

}
