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
    private LocalizacaoService localizacaoservice;

    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Cadastrar localização", description = "Faz o cadastro da localização")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity cadastrar(@RequestBody LocalizacaoModel localizacao) {

        try {
            localizacaoservice.cadastrar(localizacao);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível adicionar a localização.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Localização adicionada!", HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Listar as localizações", description = "Faz a listagem de todas as localizações")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listar() {

        try {
            return new ResponseEntity(localizacaoservice.listar(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listar/{codigo}")
    @Operation(summary = "Listar a localização por código", description = "Faz a listagem da localização referente ao código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listarPorCodigo(@PathVariable Integer codigo) {

        try {
            return new ResponseEntity(localizacaoservice.buscarCodigo(codigo), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity( "Código Inválido!", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/alterar/{codigo}")
    @Operation(summary = "Alterar as localizações", description = "Faz a alteração das localizações baseado no código informado, com alterações atualizadas no body")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity alterar(@PathVariable Integer codigo ,
                                  @RequestBody LocalizacaoModel localizacao){

        try {
            localizacaoservice.update(codigo, localizacao);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível alterar", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(localizacao, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar/{codigo}")
    @Operation(summary = "Deletar as localizações", description = "Faz a exclusão da localização escolhida pelo código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity deletar(@PathVariable Integer codigo){

        try {
            localizacaoservice.remover(codigo);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Código inválido!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Localização do código " + codigo + " foi removida! ", HttpStatus.OK);
    }
}
