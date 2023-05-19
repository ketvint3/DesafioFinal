package com.Desafio.Final.Diamond.Controllers;


import com.Desafio.Final.Diamond.Models.PagamentoModel;
import com.Desafio.Final.Diamond.Services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

      @Autowired
       private PagamentoService pagamentoService;

     @PostMapping
     public PagamentoModel savePagamento(@RequestBody PagamentoModel pagamentoModel) {
            return pagamentoService.savePagamento(pagamentoModel);
     }

     @DeleteMapping("/{id}")
     public void deletePagamentoModel(@PathVariable Integer id) {
            pagamentoService.deletePagamentoModel(id);
     }

     @GetMapping("/{id}/calcular")
     public ResponseEntity<BigDecimal> calcularPagamento(@PathVariable Integer id, @RequestParam Double kmRodado) {
        BigDecimal valorFinal = pagamentoService.calcularPagamento(id, kmRodado);

         if (valorFinal == null) {
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok(valorFinal);
        }
    }

