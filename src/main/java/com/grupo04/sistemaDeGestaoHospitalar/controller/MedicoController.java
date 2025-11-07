package com.grupo04.sistemaDeGestaoHospitalar.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Medico;
import com.grupo04.sistemaDeGestaoHospitalar.service.MedicoService;

import java.util.List;

@RestController
@RequestMapping("/medicos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MedicoController {

    private final MedicoService service;

    @GetMapping
    public List<Medico> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscar(@PathVariable Integer id) {
        return service.buscar(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Medico salvar(@RequestBody Medico obj) { return service.salvar(obj); }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizar(@PathVariable Integer id, @RequestBody Medico novo) {
        try { return ResponseEntity.ok(service.atualizar(id, novo)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

