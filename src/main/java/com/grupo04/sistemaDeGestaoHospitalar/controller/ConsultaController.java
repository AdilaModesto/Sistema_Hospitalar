package com.grupo04.sistemaDeGestaoHospitalar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Consulta;
import com.grupo04.sistemaDeGestaoHospitalar.service.ConsultaService;

import java.util.List;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ConsultaController {

    private final ConsultaService service;

    @GetMapping
    public List<Consulta> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscar(@PathVariable Integer id) {
        return service.buscar(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Consulta salvar(@RequestBody Consulta obj) { return service.salvar(obj); }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizar(@PathVariable Integer id, @RequestBody Consulta novo) {
        try { return ResponseEntity.ok(service.atualizar(id, novo)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

