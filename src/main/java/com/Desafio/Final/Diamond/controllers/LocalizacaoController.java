package com.Desafio.Final.Diamond.controllers;

import com.Desafio.Final.Diamond.models.LocalizacaoModel;
import com.Desafio.Final.Diamond.services.LocalizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/localizacao")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService service;

    @PostMapping(value = "/add")
    public ResponseEntity localizacaoAdicionada(@RequestBody LocalizacaoModel localizacao) {
        service.adicionar(localizacao);
        return new ResponseEntity(localizacao, HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar")
    public ResponseEntity listarPorCodigo() {
        return new ResponseEntity(service.listar(), HttpStatus.OK);
    }

    @PutMapping(value = "/alterar/{codigo}")
    public ResponseEntity alterar(@PathVariable Integer codigo ,
                                  @RequestBody LocalizacaoModel localizacao){
        service.update(codigo, localizacao);
        return new ResponseEntity(localizacao, HttpStatus.OK);
    }
    @DeleteMapping(value = "/deletar/{codigo}")
    public ResponseEntity deletar(@PathVariable Integer codigo){
        service.remover(codigo);
        return new ResponseEntity(HttpStatus.OK);
    }
}
