package com.Desafio.Final.Diamond.controllers;

import com.Desafio.Final.Diamond.models.LocalizacaoModel;
import com.Desafio.Final.Diamond.models.MotoristaModel;
import com.Desafio.Final.Diamond.models.PassageiroModel;
import com.Desafio.Final.Diamond.models.ViagemModel;
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
    @Operation(summary = "Pedir viagem", description = "Faz o pedido das viagens")
    @ApiResponse(responseCode = "200", description = "Sucesso!")


    public ResponseEntity cadastrarAgenda(@RequestBody ViagemModel viagem,
                                          @RequestParam Integer codigoPassageiro,
                                          @RequestParam Integer codigoMotorista,
                                          @RequestParam Integer codigoLocalizacao) {

        PassageiroModel passageiro = passageiroService.buscarPassageiro(codigoPassageiro);
        viagem.setPassageiroId(passageiro);

        MotoristaModel motorista = motoristaService.pesquisarMotoristaPorId(codigoMotorista);
        viagem.setMotoristaId(motorista);

        LocalizacaoModel localizacao = localizacaoService.acharPorCodigo(codigoLocalizacao);
        viagem.setLocalizacaoId(localizacao);


        try {
            viagemService.cadastrar(viagem);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível cadastrar a viagem.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Viagem cadastrada!", HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Lista as viagens", description = "Faz a listagem de todas as viagens")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listarAgendas() {

        try {
            return new ResponseEntity(viagemService.listar(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listar/{codigo}")
    @Operation(summary = "Lista viagens por código", description = "Faz a listagem da viagem referente ao código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity listarPorCodigo(@PathVariable Integer codigo) {

        try {
            return new ResponseEntity(viagemService.listarCodigo(codigo), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/alterar/{codigo}")
    @Operation(summary = "Altera as viagens", description = "Faz a alteração das viagens baseado no código informado, com alterações atualizadas no body")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity alterar(@PathVariable Integer codigo, @RequestBody ViagemModel viagem) {

        try {
            viagemService.update(codigo, viagem);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível alterar", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(viagem, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar/{codigo}")
    @Operation(summary = "Deleta as viagens", description = "Faz a exclusão da viagem escolhida pelo código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")

    public ResponseEntity deletar(@PathVariable Integer codigo) {

        try {
            viagemService.remover(codigo);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Código inválido!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Viagem do código " + codigo + " foi removida! ", HttpStatus.OK);
    }
}
