package com.Desafio.Final.Diamond.repositories.facade.impl;

import com.Desafio.Final.Diamond.models.MotoristaModel;
import com.Desafio.Final.Diamond.models.PagamentoModel;
import com.Desafio.Final.Diamond.models.ValorModel;
import com.Desafio.Final.Diamond.repositories.facade.MetodosFacade;
import com.Desafio.Final.Diamond.services.MotoristaService;
import com.Desafio.Final.Diamond.services.PagamentoService;
import com.Desafio.Final.Diamond.services.ValorService;
import org.springframework.beans.factory.annotation.Autowired;

public class MetodosFacadeImpl implements MetodosFacade {
    @Autowired
    private MotoristaService motoristaService;
    @Autowired
    private ValorService valorService;
    @Autowired
    private PagamentoService pagamentoService;

    @Override
    public MotoristaModel buscarCodigoDeMotorista(Integer motoristaId) {
        MotoristaModel motorista = motoristaService.buscarCodigo(motoristaId);

        motorista.setCodigo(motoristaId);
        return motorista;
    }

    @Override
    public ValorModel buscarCodigoDeValor(Integer valorId) {
        ValorModel valor = valorService.buscarCodigo(valorId);

        valor.setCodigo(valorId);
        return valor;
    }

    @Override
    public void salvarPagamento(PagamentoModel pagamento) {

        pagamentoService.savePagamento(pagamento);

    }
}
