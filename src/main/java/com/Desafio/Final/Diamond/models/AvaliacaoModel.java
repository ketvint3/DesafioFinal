package com.Desafio.Final.Diamond.models;

import com.Desafio.Final.Diamond.models.enu.AvaliacaoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "tb_avaliacao")
public class AvaliacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private Integer codigo;
    @NotNull
    private AvaliacaoEnum avaliacao;

}
