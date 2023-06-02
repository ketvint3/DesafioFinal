package com.Desafio.Final.Diamond.models;

import com.Desafio.Final.Diamond.models.enu.AvaliacaoEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_avaliacao")
public class AvaliacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;
    private AvaliacaoEnum avaliacao;

}
