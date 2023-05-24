package com.Desafio.Final.Diamond.controllers;

import com.Desafio.Final.Diamond.models.*;
import com.Desafio.Final.Diamond.models.enu.ViagemEnum;

import com.Desafio.Final.Diamond.repositories.facade.impl.AceitarViagemFacadeImpl;
import com.Desafio.Final.Diamond.repositories.facade.impl.FinalizarViagemFacadeImpl;
import com.Desafio.Final.Diamond.services.*;
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
    private PagamentoService pagamentoService;
    @Autowired
    private AceitarViagemFacadeImpl aceitarViagemFacade;
    @Autowired
    private FinalizarViagemFacadeImpl finalizarViagemFacade;

    @PostMapping(value = "/cadastrar")
    @Operation(summary = "Solicitar viagem", description = "Faz a solicitação das viagens")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity cadastrar(@RequestBody ViagemModel viagem,
                                    @RequestParam Integer codigoPassageiro,
                                    @RequestParam(required = false) Integer codigoPagamento) {

        PassageiroModel passageiro = passageiroService.buscarCodigo(codigoPassageiro);
        viagem.setPassageiro(passageiro);

        PagamentoModel pagamento = pagamentoService.buscarCodigo(codigoPagamento);
        viagem.setPagamento(pagamento);

        viagem.setStatusViagem(ViagemEnum.PENDENTE);

        try {
            viagemService.cadastrar(viagem);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Não foi possível solicitar a viagem.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Viagem solicitada!", HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Listar as viagens", description = "Faz a listagem de todas as viagens")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listar() {

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
            return new ResponseEntity(viagemService.buscarCodigo(codigo), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity( "Código Inválido!", HttpStatus.NOT_FOUND);
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
    @PutMapping("/cancelar/{codigo}")
    @Operation(summary = "Cancelar uma viagem", description = "Método da api onde a viagem é solicitada ou cancelada")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity cancelarviagem(@PathVariable Integer codigo) {

        ViagemModel viagem = viagemService.buscarCodigo(codigo);

        if (viagem != null) {
            viagem.setStatusViagem(ViagemEnum.CANCELADA);
            viagemService.update(codigo, viagem);
            return new ResponseEntity("Sua viagem foi cancelada!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Viagem inválida!", HttpStatus.BAD_REQUEST);

        }
    }

    // MOTORISTA
    @PutMapping("/aceitar/{codigo}")
    @Operation(summary = "Aceitar uma viagem", description = "Método da api onde a viagem é aceita ou cancelada")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity aceitarViagem(@PathVariable Integer codigo,
                                        @RequestParam Integer motoristaId) {

        boolean aceitarViagem = aceitarViagemFacade.aceitarViagem(codigo, motoristaId);

        if (aceitarViagem) {
            return new ResponseEntity<>("Viagem aceita com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity("Viagem inválida!", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/finalizar/{codigo}")
    @Operation(summary = "Finalizar uma viagem", description = "Método da api onde a viagem é finalizada")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity finalizarViagem(@PathVariable Integer codigo,
                                          @RequestBody PagamentoModel pagamento,
                                          @RequestParam Integer codigoPagamento) {

        boolean finalizarViagem = finalizarViagemFacade.finalizarViagem(codigo, pagamento, codigoPagamento);

            if (finalizarViagem) {
            return new ResponseEntity("Sua viagem foi finalizada!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Viagem inválida!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/pendente")
    @Operation(summary = "Listar viagens pendentes", description = "Método da api onde é feita a listagem das viagens pendentes")
    @ApiResponse(responseCode = "200", description = "Operação concluida com sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listarViagensPendentes() {

        return new ResponseEntity(viagemService.listarPendentes(), HttpStatus.OK);
    }
}


