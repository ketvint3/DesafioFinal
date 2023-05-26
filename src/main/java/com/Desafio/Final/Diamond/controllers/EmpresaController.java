package com.Desafio.Final.Diamond.controllers;

import com.Desafio.Final.Diamond.models.EmpresaModel;
import com.Desafio.Final.Diamond.services.EmpresaService;
import com.Desafio.Final.Diamond.services.MotoristaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private MotoristaService motoristaService;


    @PostMapping
    @Operation(summary = "Cadastrar empresa", description = "Faz o cadastro de empresas")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity empresa(@RequestBody EmpresaModel empresaModel){

      try {
          empresaService.adicionar(empresaModel);
          return new ResponseEntity(HttpStatus.CREATED);
      } catch (NoSuchElementException e) {
          return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
      }

    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Listar as empresas", description = "Faz a listagem de todas as empresas")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listarEmpresa() {

      try {
          empresaService.listarTudo();
          return new ResponseEntity(empresaService.listarTudo(), HttpStatus.OK);
      } catch (NoSuchElementException e) {
          return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
      }
    }
    @GetMapping(value = "/listar/{codigo}")
    @Operation(summary = "Listar empresas por código", description = "Faz a listagem da empresa referente ao código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity buscarEmpresa(@PathVariable Integer codigo) {

       try {
           empresaService.buscar(codigo);
           return new ResponseEntity(codigo, HttpStatus.OK);
       } catch (NoSuchElementException e) {
           return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping(value = "/alterar/{codigo}")
    @Operation(summary = "Alterar as empresas", description = "Faz a alteração das empresas baseado no código informado, com alterações atualizadas no body")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity atualizarEmpresa(@PathVariable Integer codigo,
                                           @RequestBody EmpresaModel empresaModel){
       try {
           empresaService.update(codigo);
           return new ResponseEntity("Empresa do código " + codigo + " foi alterada!", HttpStatus.OK);
       } catch (NoSuchElementException e) {
           return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
       }
    }

    @DeleteMapping(value = "/deletar/{codigo}")
    @Operation(summary = "Deletar as viagens", description = "Faz a exclusão da viagem escolhida pelo código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity deletar(@PathVariable Integer codigo) {

        try {
            empresaService.remover(codigo);
            return new ResponseEntity( "Empresa do código " + codigo + " foi removida!", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping("/calcular/{codigo}")


    public ResponseEntity<BigDecimal> calcularTaxa(@PathVariable Integer codigo,
                                                   @RequestParam Double kmRodado) {

        BigDecimal precoTotal = empresaService.calcularTaxa(codigo, kmRodado);

        if (precoTotal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(precoTotal);
    }
}
