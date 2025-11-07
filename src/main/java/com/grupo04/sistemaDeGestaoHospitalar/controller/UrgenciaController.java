package com.grupo04.sistemaDeGestaoHospitalar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Urgencia;
import com.grupo04.sistemaDeGestaoHospitalar.service.UrgenciaService;

import java.util.List;

@RestController
@RequestMapping("/urgencias")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UrgenciaController {

    private final UrgenciaService service;

    @GetMapping
    public List<Urgencia> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<Urgencia> buscar(@PathVariable Integer id) {
        return service.buscar(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Urgencia salvar(@RequestBody Urgencia obj) { return service.salvar(obj); }

    @PutMapping("/{id}")
    public ResponseEntity<Urgencia> atualizar(@PathVariable Integer id, @RequestBody Urgencia novo) {
        try { return ResponseEntity.ok(service.atualizar(id, novo)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

