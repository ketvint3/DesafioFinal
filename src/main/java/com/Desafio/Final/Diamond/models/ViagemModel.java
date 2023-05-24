package com.Desafio.Final.Diamond.models;

import com.Desafio.Final.Diamond.models.enu.ViagemEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "tb_viagem")
public class ViagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;
    @NotNull
    @Column(length = 255)
    private String partida;
    @NotNull
    @Column(length = 255)
    private String chegada;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status_viagem")
    private ViagemEnum statusViagem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motorista_id")
    private MotoristaModel motorista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passageiro_id")
    private PassageiroModel passageiro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pagamento_id")
    private PagamentoModel pagamento;
}