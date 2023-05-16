package com.Desafio.Final.Diamond.Models;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_empresa")
public class EmpresaModel {
    private String cnpj;
    private String email;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    private EmpresaModel empresaModel;
    @ManyToOne
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;
    @ManyToOne
    @JoinColumn(name = "suporte_id")
    private Suporte suporte;



}
