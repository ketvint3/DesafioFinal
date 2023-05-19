package com.Desafio.Final.Diamond.controllers;

import com.Desafio.Final.Diamond.models.LocalizacaoModel;
import com.Desafio.Final.Diamond.services.LocalizacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/localizacao")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService service;

    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Cadastrar localização", description = "Faz o cadastro da localização")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity localizacaoAdicionada(@RequestBody LocalizacaoModel localizacao) {
        service.adicionar(localizacao);
        return new ResponseEntity(localizacao, HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Listar as localizações", description = "Faz a listagem de todas as localizações")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listarPorCodigo() {
        return new ResponseEntity(service.listar(), HttpStatus.OK);
    }

    @GetMapping(value = "/listar/{codigo}")
    @Operation(summary = "Listar a localização por código", description = "Faz a listagem da localização referente ao código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listarPorCodigo(@PathVariable Integer codigo) {

        try {
            return new ResponseEntity(service.acharPorCodigo(codigo), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/alterar/{codigo}")
    @Operation(summary = "Alterar as localizações", description = "Faz a alteração das localizações baseado no código informado, com alterações atualizadas no body")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity alterar(@PathVariable Integer codigo ,
                                  @RequestBody LocalizacaoModel localizacao){
        service.update(codigo, localizacao);
        return new ResponseEntity(localizacao, HttpStatus.OK);
    }
    @DeleteMapping(value = "/deletar/{codigo}")
    @Operation(summary = "Deletar as localizações", description = "Faz a exclusão da localização escolhida pelo código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity deletar(@PathVariable Integer codigo){
        service.remover(codigo);
        return new ResponseEntity(HttpStatus.OK);
    }
}
