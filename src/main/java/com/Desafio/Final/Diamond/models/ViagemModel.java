package com.Desafio.Final.Diamond.models;

import com.Desafio.Final.Diamond.models.enu.ViagemEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_viagem")
public class ViagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;
    @Column(length = 255)
    private String partida;
    @Column(length = 255)
    private String chegada;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_viagem")
    private ViagemEnum statusViagem;

    @ManyToOne
    @JoinColumn(name = "motorista_id")
    private MotoristaModel motorista;
    @ManyToOne
    @JoinColumn(name = "passageiro_id")
    private PassageiroModel passageiro;

    @ManyToOne
    @JoinColumn(name = "localizacao_id")
    private LocalizacaoModel localizacao;

}