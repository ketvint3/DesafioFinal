package com.Desafio.Final.Diamond.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;


@Data
@Entity(name = "tb_empresa")
public class EmpresaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;
    @CNPJ
    @NotNull
    private String cnpj;
    @Email
    @NotNull
    private String email;
    @ManyToOne
    @JoinColumn(name = "motorista_id")
    private MotoristaModel motoristaid;
    @ManyToOne
    @JoinColumn(name = "pagamento_id")
    private PagamentoModel pagamento;

}
