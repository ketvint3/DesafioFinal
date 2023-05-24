package com.Desafio.Final.Diamond.Controllers;
import com.Desafio.Final.Diamond.Models.EmpresaModel;
import com.Desafio.Final.Diamond.Serveces.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "Empresa")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private MotoristaService motoristaService;


    //adicionar
    @PostMapping
    public ResponseEntity empresa(@RequestBody EmpresaModel empresaModel){
        empresaService.adicionar(empresaModel);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    //listar
    @GetMapping(value = "/listar")
    public ResponseEntity listarEmpresa(){
        empresaService.listarTudo();
        return new ResponseEntity(empresaService.listarTudo(), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar")
    public ResponseEntity buscarEmpresa(@PathVariable Integer codigo){
        empresaService.buscar(codigo);
        return new ResponseEntity(codigo, HttpStatus.OK);
    }

    //atualizar
    @PutMapping(value = "/atualizar")
    public ResponseEntity atualizarEmpresa(@PathVariable Integer codigo, @RequestBody EmpresaModel empresaModel){
        empresaService.update(codigo);
        return new ResponseEntity(codigo, HttpStatus.OK);
    }

    //remover
    @DeleteMapping(value = "/deletar")
    public ResponseEntity deletar(@PathVariable Integer codigo){
        empresaService.remover(codigo);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/{codigo}/calcular")
    @Operation(summary = "Calcula o total da taxa", description = "Faz a soma da taxa")
    public ResponseEntity<BigDecimal> calcularTaxa(@PathVariable Integer codigo,
                                                   @RequestParam Double kmRodado) {

        BigDecimal precoTotal = empresaService.calcularTaxa(codigo, kmRodado);

        if (precoTotal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(precoTotal);
    }
}

