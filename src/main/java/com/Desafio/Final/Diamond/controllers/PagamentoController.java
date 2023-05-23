package com.Desafio.Final.Diamond.controllers;

import com.Desafio.Final.Diamond.models.PagamentoModel;
import com.Desafio.Final.Diamond.services.PagamentoService;
import com.Desafio.Final.Diamond.services.ValorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private ValorService valorService;

    @PostMapping
    @Operation(summary = "Adiciona o valor do pagamento", description = "Adiciona os valores do pagamento, como: taxa por km, pagamento base e quantos km foram rodados")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity savePagamento(@RequestBody PagamentoModel pagamentoModel,
                                        @RequestParam Integer valorId) {

        pagamentoModel.setValor(valorService.buscarCodigo(valorId));
        pagamentoService.savePagamento(pagamentoModel);

        return new ResponseEntity("Seu pagamento foi adicionado! ", HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Listar os pagamentos", description = "Faz a listagem de todos pagamentos")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity listar() {

        try {
            return new ResponseEntity(pagamentoService.listar(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Erro! Tente novamente.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{codigo}")
    @Operation(summary = "Deletar os pagamentos", description = "Faz a exclusão do pagamento escolhido pelo código informado")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public void deletePagamentoModel(@PathVariable Integer codigo) {

        pagamentoService.deletePagamentoModel(codigo);
    }
}
