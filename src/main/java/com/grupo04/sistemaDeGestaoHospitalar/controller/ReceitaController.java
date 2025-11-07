package com.grupo04.sistemaDeGestaoHospitalar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Receita;
import com.grupo04.sistemaDeGestaoHospitalar.service.ReceitaService;

import java.util.List;

@RestController
@RequestMapping("/receitas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReceitaController {

    private final ReceitaService service;

    @GetMapping
    public List<Receita> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> buscar(@PathVariable Integer id) {
        return service.buscar(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Receita salvar(@RequestBody Receita obj) { return service.salvar(obj); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
