package com.Desafio.Final.Diamond.controllers;

import com.Desafio.Final.Diamond.models.ValorModel;
import com.Desafio.Final.Diamond.services.ValorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class ValorController {

    @Autowired
    private ValorService valorService;

    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Cadastrar valor", description = "Faz o cadastro dos valores")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity cadastrar(@RequestBody ValorModel valor) {

        try {
            valorService.adicionar(valor);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível cadastrar o valor.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Valor cadastrado!", HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Listar os valores", description = "Faz a listagem de todos os valores")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listar() {

        try {
            return new ResponseEntity(valorService.listar(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listar/{codigo}")
    @Operation(summary = "Listar valores por código", description = "Faz a listagem do valor referente ao código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listarPorCodigo(@PathVariable Integer codigo) {

        try {
            return new ResponseEntity(valorService.buscarCodigo(codigo), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Código Inválido!", HttpStatus.NOT_FOUND);
        }
    }

        @PutMapping(value = "/alterar/{codigo}")
        @Operation(summary = "Alterar os valores", description = "Faz a alteração dos valores baseado no código informado, com alterações atualizadas no body")
        @ApiResponse(responseCode = "200", description = "Sucesso!")
        @ApiResponse(responseCode = "404", description = "Erro na operação!")
        @ApiResponse(responseCode = "500", description = "Erro inesperado!")

        public ResponseEntity alterar(@PathVariable Integer codigo, @RequestBody ValorModel valorModel) {

            try {
                valorService.alterar(codigo, valorModel);
            } catch (NoSuchElementException e) {
                return new ResponseEntity("Não foi possível alterar", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(valorModel, HttpStatus.OK);
        }

        @DeleteMapping(value = "/deletar/{codigo}")
        @Operation(summary = "Deletar os valores", description = "Faz a exclusão do valor escolhido pelo código informado")
        @ApiResponse(responseCode = "200", description = "Sucesso!")
        @ApiResponse(responseCode = "404", description = "Erro na operação!")
        @ApiResponse(responseCode = "500", description = "Erro inesperado!")

        public ResponseEntity deletar(@PathVariable Integer codigo) {

            try {
                valorService.deletar(codigo);
            } catch (NoSuchElementException e) {
                return new ResponseEntity("Código inválido!", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity("Valor do código " + codigo + " foi removido! ", HttpStatus.OK);
        }
    }

