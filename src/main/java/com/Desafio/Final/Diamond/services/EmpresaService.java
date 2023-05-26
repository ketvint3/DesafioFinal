package com.Desafio.Final.Diamond.services;

import com.Desafio.Final.Diamond.models.EmpresaModel;
import com.Desafio.Final.Diamond.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;


@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    //adicionar
    public void adicionar(EmpresaModel empresaModel) {
        empresaRepository.save(empresaModel);
    }

    //buscar
    public EmpresaModel buscar(Integer codigo) {
        Optional<EmpresaModel> optempresa = empresaRepository.findById(codigo);
        return optempresa.get();
    }

    //listar
    public Object listarTudo() {
        return empresaRepository.findAll();

    }

    //atualizar
    public void update(Integer codigo) {
        if (empresaRepository.existsById(codigo)) {
            empresaRepository.deleteById(codigo);
        }

    }

    //remover
    public void remover(Integer codigo) {
        empresaRepository.deleteById(codigo);

    }

    // precoKm = distancia * taxakm
    // precoTotal = precoKm + taxaBase
    public BigDecimal calcularTaxa(Integer codigo, Double distancia) {

        return null;
    }
}
