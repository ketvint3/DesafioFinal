package com.Desafio.Final.Diamond.controllers;

import com.Desafio.Final.Diamond.models.DetalhePagamentoModel;
import com.Desafio.Final.Diamond.services.DetalhePagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/detalhe")
public class DetalhePagamentoController {

    @Autowired
    private DetalhePagamentoService detalhePagamentoService;

    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Cadastrar detalhe de pagamento", description = "Faz o cadastro dos detalhes do pagamento")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity cadastrar(@RequestBody DetalhePagamentoModel detalhePagamento) {

        try {
            detalhePagamentoService.adicionar(detalhePagamento);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível cadastrar o detalhe do pagamento.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Detalhe de pagamento cadastrado!", HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Listar os detalhes de pagamento", description = "Faz a listagem de todos os detalhes de pagamentos")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listar() {

        try {
            return new ResponseEntity(detalhePagamentoService.listar(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listar/{codigo}")
    @Operation(summary = "Listar detalhes do pagamento por código", description = "Faz a listagem do detalhe do pagamento referente ao código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listarPorCodigo(@PathVariable Integer codigo) {

        try {
            return new ResponseEntity(detalhePagamentoService.buscarCodigo(codigo), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Código Inválido!", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/alterar/{codigo}")
    @Operation(summary = "Alterar os detalhes de pagamento", description = "Faz a alteração dos detalhes de pagamento baseado no código informado, com alterações atualizadas no body")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity alterar(@PathVariable Integer codigo,
                                  @RequestBody DetalhePagamentoModel detalhePagamento) {

        try {
            detalhePagamentoService.alterar(codigo, detalhePagamento);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível alterar", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(detalhePagamento, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar/{codigo}")
    @Operation(summary = "Deletar os detalhes de pagamento", description = "Faz a exclusão dos detalhe dos pagamentos escolhido pelo código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity deletar(@PathVariable Integer codigo) {

        try {
            detalhePagamentoService.deletar(codigo);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Código inválido!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Detalhe do pagamento do código " + codigo + " foi removido! ", HttpStatus.OK);
    }
}
