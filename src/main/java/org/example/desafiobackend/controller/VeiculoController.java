package org.example.desafiobackend.controller;

import org.example.desafiobackend.model.Veiculo;
import org.example.desafiobackend.service.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public List<Veiculo> obterTodos() {
        return veiculoService.obterTodos();
    }

    @GetMapping("/{id}")
    public Veiculo obterVeiculoPeloId(@PathVariable Long id) {
        return veiculoService.obterVeiculoPeloId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Veiculo inserirVeiculo(@RequestBody Veiculo veiculo) {
        return veiculoService.inserirVeiculo(veiculo);
    }

    @PutMapping
    public Veiculo atualizarVeiculo(@RequestBody Veiculo veiculo) {
        return veiculoService.atualizarVeiculo(veiculo);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void excluirVeiculo(@PathVariable Long id) {
        veiculoService.excluirVeiculoPeloId(id);
    }
}
