package com.Desafio.Final.Diamond.Controllers;
import com.Desafio.Final.Diamond.Models.PagamentoModel;
import com.Desafio.Final.Diamond.Serveces.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    @Operation(summary = "Adiciona o valor do pagamento", description = "Adiciona os valores do pagamento, como: taxa por km, pagamento base e quantos km foram rodados")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public PagamentoModel savePagamento(@RequestBody PagamentoModel pagamentoModel) {
        return pagamentoService.savePagamento(pagamentoModel);
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

    @GetMapping("/{codigo}/calcular")
    @Operation(summary = "Calcula o total do pagamento", description = "Faz a soma do pagamento")
    @ApiResponse(responseCode = "200", description = "Sucesso!")
    @ApiResponse(responseCode = "404", description = "Erro na operação!")
    @ApiResponse(responseCode = "500", description = "Erro inesperado!")

    public ResponseEntity<BigDecimal> calcularPagamento(@PathVariable Integer codigo,
                                                        @RequestParam Double kmRodado) {

        BigDecimal valorFinal = pagamentoService.calcularPagamento(codigo, kmRodado);

        if (valorFinal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(valorFinal);
    }
}

