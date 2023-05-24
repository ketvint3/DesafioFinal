
package com.Desafio.Final.Diamond.Models;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import java.math.BigDecimal;

@Data
@Entity(name = "tb_empresa")
public class EmpresaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @CNPJ
    private String cnpj;
    @Email
    private String email;
    @ManyToOne
    @JoinColumn(name = "motorista_id")
    private MotoristaModel motoristaid;
    private PagamentoModel pagamentoModel;


}

