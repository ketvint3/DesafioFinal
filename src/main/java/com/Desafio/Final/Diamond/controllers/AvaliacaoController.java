package com.Desafio.Final.Diamond.controllers;

import com.Desafio.Final.Diamond.models.AvaliacaoModel;

import com.Desafio.Final.Diamond.services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/avaliacao")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping(value = "/listar")
    public ResponseEntity ListarPorAvaliacao(AvaliacaoModel avaliacao){
        avaliacaoService.listar(avaliacao);
        return new ResponseEntity(avaliacao, HttpStatus.OK);
    }

    @PostMapping(value = "/adicionar")
    public ResponseEntity novaAvaliacao(@RequestBody AvaliacaoModel avaliacao){
        avaliacaoService.adicionar(avaliacao);
        return new ResponseEntity(avaliacao, HttpStatus.CREATED);
    }
    @PutMapping(value = "/alterar")
    public ResponseEntity alterar (@PathVariable Integer codigo,
                                   @RequestBody AvaliacaoModel avaliacao){
        return new ResponseEntity<>(codigo, HttpStatus.OK);

    }

    @DeleteMapping(value = "/deletar/{codigo}")
    public ResponseEntity deletar (@PathVariable Integer codigo){
        return new ResponseEntity(HttpStatus.OK);
    }


















}
