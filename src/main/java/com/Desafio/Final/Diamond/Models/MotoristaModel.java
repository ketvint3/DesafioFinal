package com.Desafio.Final.Diamond.Models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Data
@Entity(name = "tb_motorista")
public class MotoristaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private Integer codigo;
    @NotNull
    @Size(min = 3, max = 55, message = "" +
            "Nome deve conter no minimo 3 caracteres")
    private String nome;
    @CPF
    @NotNull
    private String cpf;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String veiculo;
}