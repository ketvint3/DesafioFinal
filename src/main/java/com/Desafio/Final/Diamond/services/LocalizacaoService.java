package com.Desafio.Final.Diamond.services;

import com.Desafio.Final.Diamond.models.LocalizacaoModel;
import com.Desafio.Final.Diamond.repositories.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalizacaoService {

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    public void cadastrar(LocalizacaoModel localizacaoSalva) {
        localizacaoRepository.save(localizacaoSalva);

    }
    public List<LocalizacaoModel> listar() {
        return localizacaoRepository.findAll();


    }
    public LocalizacaoModel buscarCodigo(Integer codigo) {
        Optional<LocalizacaoModel> optionalLocalizacao = localizacaoRepository.findById(codigo);
        if (optionalLocalizacao.isEmpty()) {

            return null;
        }
        return optionalLocalizacao.get();
    }
    public void update(Integer codigo, LocalizacaoModel localizacao) {
        if (localizacaoRepository.existsById(codigo)) {
            localizacaoRepository.save(localizacao);

        }
    }
    public void remover(Integer codigo) {

        if (localizacaoRepository.existsById(codigo)) {
            localizacaoRepository.deleteById(codigo);

        }
    }
}


