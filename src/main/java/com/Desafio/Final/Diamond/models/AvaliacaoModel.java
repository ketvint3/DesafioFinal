package com.Desafio.Final.Diamond.models;

import com.Desafio.Final.Diamond.models.enu.AvaliacaoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.aspectj.weaver.ast.Not;

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
