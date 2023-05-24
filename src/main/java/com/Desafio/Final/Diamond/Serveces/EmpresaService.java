package com.Desafio.Final.Diamond.Serveces;

import com.Desafio.Final.Diamond.Models.EmpresaModel;
import com.Desafio.Final.Diamond.Repositorys.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    //adicionar
    public void adicionar(EmpresaModel empresaModel){
        empresaRepository.save(empresaModel);
    }
    //buscar
    public EmpresaModel buscar(Integer codigo){
        Optional<EmpresaModel> optempresa = empresaRepository.findById(codigo);
        return optempresa.get();
    }
    //listar
    public Object listarTudo(){
        return empresaRepository.findAll();

    }
    //atualizar
    public void update(Integer codigo){
        if (empresaRepository.existsById(codigo)){
                empresaRepository.deleteById(codigo);
            }

    }

    //remover
    public void remover(Integer codigo) {
        empresaRepository.delete(codigo);

    }

    // precoKm = distancia * precokm
    // precoTotal = precoKm + taxaBase
    public BigDecimal calcularTaxa(Integer codigo, Double distancia){
        EmpresaModel empresaModel = empresaRepository.findById(codigo).orElse(null);
        if ( empresaModel == null){
            return null;
        }

        Double precokm = distancia * empresaModel.getTaxaBase();
        BigDecimal precoTotal = empresaModel.getValorFinal().add(BigDecimal.valueOf(distancia));

        empresaModel.setKmRodado(precokm);
        empresaModel.setValorFinal(precoTotal);
        empresaRepository.save(empresaModel);

        return precoTotal;
}
