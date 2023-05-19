package com.Desafio.Final.Diamond.controllers;

import com.Desafio.Final.Diamond.models.PassageiroModel;
import com.Desafio.Final.Diamond.services.PassageiroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/passageiro")
public class PassageiroController {

    @Autowired
    private PassageiroService passageiroService;

    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Cadastrar passageiros", description = "Método da api para cadastro de passageiro na plataforma")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity cadastrar(@Valid @RequestBody PassageiroModel passageiro) {
        passageiroService.adicionarPassageiro(passageiro);
        return ResponseEntity.status(HttpStatus.CREATED).body("Passageiro adicionado com sucesso!");
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Listar passageiros", description = "Método da api para listagem de todos os passageiros cadastrados no banco.")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listar() {
        return new ResponseEntity<>(passageiroService.listarPassageiros(), HttpStatus.OK);
    }

    @GetMapping(value = "/listar/{id}")
    @Operation(summary = "Listar código do passageiro", description = "Método da api para listagem do passageiro, conforme seu código informado na plataforma")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listarCodigo(@PathVariable Integer id) {
        PassageiroModel passageiro = passageiroService.buscarCodigo(id);
        if (passageiro != null) {
            return ResponseEntity.ok(passageiro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping(value = "/alterar/{id}")
    @Operation(summary = "Atualizar passageiro", description = "Método da api para alterar os dados de um passageiro")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity atualizar(@PathVariable Integer codigo, @RequestBody PassageiroModel passageiro) {

        try {
            passageiroService.atualizarPassageiro(codigo, passageiro);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível alterar", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(passageiro, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar/{id}")
    @Operation(summary = "Deletar passageiro", description = "Método da api para exclusão de um passageiro da plataforma")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity deletar(@PathVariable Integer id) {
        PassageiroModel passageiro = passageiroService.buscarCodigo(id);
        if (passageiro != null) {
            passageiroService.removerPassageiro(id);
            return ResponseEntity.ok("Passageiro removido com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
