package com.grupo04.sistemaDeGestaoHospitalar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Exame;
import com.grupo04.sistemaDeGestaoHospitalar.service.ExameService;

import java.util.List;

@RestController
@RequestMapping("/exames")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExameController {

    private final ExameService service;

    @GetMapping
    public List<Exame> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<Exame> buscar(@PathVariable Integer id) {
        return service.buscar(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Exame salvar(@RequestBody Exame obj) { return service.salvar(obj); }

    @PutMapping("/{id}")
    public ResponseEntity<Exame> atualizar(@PathVariable Integer id, @RequestBody Exame novo) {
        try { return ResponseEntity.ok(service.atualizar(id, novo)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
