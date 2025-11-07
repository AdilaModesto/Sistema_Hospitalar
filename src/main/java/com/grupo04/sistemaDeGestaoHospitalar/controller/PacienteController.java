package com.grupo04.sistemaDeGestaoHospitalar.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Paciente;
import com.grupo04.sistemaDeGestaoHospitalar.service.PacienteService;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PacienteController {

    private final PacienteService service;

    @GetMapping public List<Paciente> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Long id) {
        return service.buscar(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping public Paciente salvar(@RequestBody Paciente obj) { return service.salvar(obj); }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizar(@PathVariable Long id, @RequestBody Paciente novo) {
        try { return ResponseEntity.ok(service.atualizar(id, novo)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @DeleteMapping("/{id}") public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
