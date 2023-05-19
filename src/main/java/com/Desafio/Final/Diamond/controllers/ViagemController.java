package com.Desafio.Final.Diamond.controllers;

import com.Desafio.Final.Diamond.models.*;
import com.Desafio.Final.Diamond.services.LocalizacaoService;
import com.Desafio.Final.Diamond.services.MotoristaService;
import com.Desafio.Final.Diamond.services.PassageiroService;
import com.Desafio.Final.Diamond.services.ViagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/viagem")
public class ViagemController {

    @Autowired
    private ViagemService viagemService;
    @Autowired
    private PassageiroService passageiroService;
    @Autowired
    private MotoristaService motoristaService;
    @Autowired
    private LocalizacaoService localizacaoService;

    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Solicitar viagem", description = "Faz a solicitação das viagens")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity cadastrarAgenda(@RequestBody ViagemModel viagem,
                                          @RequestParam Integer codigoPassageiro,
                                          @RequestParam Integer codigoLocalizacao) {

        PassageiroModel passageiro = passageiroService.buscarCodigo(codigoPassageiro);
        viagem.setPassageiro(passageiro);

        LocalizacaoModel localizacao = localizacaoService.acharPorCodigo(codigoLocalizacao);
        viagem.setLocalizacao(localizacao);

        viagem.setStatusViagem(ViagemEnum.PENDENTE);

        try {
            viagemService.cadastrar(viagem);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível cadastrar a viagem.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Viagem cadastrada!", HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Listar as viagens", description = "Faz a listagem de todas as viagens")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listarAgendas() {

        try {
            return new ResponseEntity(viagemService.listar(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listar/{codigo}")
    @Operation(summary = "Listar viagens por código", description = "Faz a listagem da viagem referente ao código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listarPorCodigo(@PathVariable Integer codigo) {

        try {
            return new ResponseEntity(viagemService.listarCodigo(codigo), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/alterar/{codigo}")
    @Operation(summary = "Alterar as viagens", description = "Faz a alteração das viagens baseado no código informado, com alterações atualizadas no body")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity alterar(@PathVariable Integer codigo, @RequestBody ViagemModel viagem) {

        try {
            viagemService.update(codigo, viagem);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível alterar", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(viagem, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar/{codigo}")
    @Operation(summary = "Deletar as viagens", description = "Faz a exclusão da viagem escolhida pelo código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity deletar(@PathVariable Integer codigo) {

        try {
            viagemService.remover(codigo);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Código inválido!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Viagem do código " + codigo + " foi removida! ", HttpStatus.OK);
    }

    // PASSAGEIRO
    @PutMapping("/cancelar/{id}")
    @Operation(summary = "Cancelar uma viagem", description = "Método da api onde a viagem é solicitada ou cancelada")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity cancelarviagem(@PathVariable Integer id) {
        ViagemModel viagem = viagemService.listarCodigo(id);

        if (viagem != null) {
            viagem.setStatusViagem(ViagemEnum.CANCELADA);
            viagemService.update(id, viagem);
            return new ResponseEntity("Sua viagem foi cancelada!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Viagem inválida!", HttpStatus.BAD_REQUEST);

        }
    }
    
    // MOTORISTA
    @PutMapping("/aceitar/{id}")
    @Operation(summary = "Aceitar uma viagem", description = "Método da api onde a viagem é aceita ou cancelada")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity aceitarViagem(@PathVariable Integer id,
                                        @RequestParam Integer motoristaId) {

        ViagemModel viagemModel = viagemService.listarCodigo(id);

        if (viagemModel != null) {
            viagemModel.setStatusViagem(ViagemEnum.EM_ANDAMENTO);

            MotoristaModel motoristaModel = motoristaService.listarCodigo(motoristaId);
            viagemModel.setMotorista(motoristaModel);

            viagemService.update(id, viagemModel);
            return new ResponseEntity<>("Viagem aceita com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity("Viagem inválida!", HttpStatus.BAD_REQUEST);
        }
    }
}

