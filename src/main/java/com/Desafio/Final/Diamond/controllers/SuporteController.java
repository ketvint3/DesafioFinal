package com.Desafio.Final.Diamond.controllers;

import com.Desafio.Final.Diamond.models.SuporteModel;
import com.Desafio.Final.Diamond.services.SuporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/suporte")
public class SuporteController {

    @Autowired
    private SuporteService service;

    @GetMapping(value = "/listar")
    @Operation(summary = "Lista os suportes", description = "Faz a listagem de todos os suportes")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listarPorCodigo(){
        return new ResponseEntity(service.listar(), HttpStatus.OK);
    }

    @GetMapping(value = "/listar/{codigo}")
    @Operation(summary = "Lista o suporte por código", description = "Faz a listagem do suporte referente ao código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listarPorCodigo(@PathVariable Integer codigo) {

        try {
            return new ResponseEntity(service.listarCodigo(codigo), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Cadastrar suporte", description = "Faz o cadastro do suporte")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity suporteAdicionado (@RequestBody SuporteModel suporte){
        service.cadastrar( suporte);
        return new ResponseEntity(suporte, HttpStatus.CREATED);
    }
    @PutMapping(value = "/alterar/{codigo}")
    @Operation(summary = "Altera os suportes", description = "Faz a alteração dos suportes baseado no código informado, com alterações atualizadas no body")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity alterar(@PathVariable Integer codigo ,
                                  @RequestBody SuporteModel suporte){
        service.update(codigo, suporte);
        return new ResponseEntity(suporte, HttpStatus.OK);
    }
    @DeleteMapping(value = "/deletar/{codigo}")
    @Operation(summary = "Deleta os suportes", description = "Faz a exclusão do suporte escolhido pelo código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity deletar(@PathVariable Integer codigo){
        service.remover(codigo);
        return new ResponseEntity(HttpStatus.OK);
    }

}
