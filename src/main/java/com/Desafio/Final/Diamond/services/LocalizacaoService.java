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

        //ADICIONANDO
        public void adicionar(LocalizacaoModel localizacaoSalva) {
            localizacaoRepository.save(localizacaoSalva);

        }

        //LISTANDO

       public List<LocalizacaoModel> listar() {
            return localizacaoRepository.findAll();


        }

        //PROCURANDO
        public Optional<LocalizacaoModel> acharPorCodigo(Integer codigo) {
            return localizacaoRepository.findById(codigo);

        }

        //ATUALIZANDO
        public void update(Integer codigo, LocalizacaoModel localizacao) {
            if (localizacaoRepository.existsById(codigo)) {
                localizacaoRepository.save(localizacao);


                //REMOVENDO
                if (localizacaoRepository.existsById(codigo)) {
                    localizacaoRepository.deleteById(codigo);
                }
            }

        }

        public void remove(Integer codigo) {
        }
    }

