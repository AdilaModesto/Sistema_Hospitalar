package com.grupo04.sistemaDeGestaoHospitalar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Resultado;
import com.grupo04.sistemaDeGestaoHospitalar.service.ResultadoService;

@RestController
@RequestMapping("/resultados")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ResultadoController {

    private final ResultadoService service;

    @GetMapping
    public List<Resultado> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<Resultado> buscar(@PathVariable Integer id) {
        return service.buscar(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Resultado salvar(@RequestBody Resultado obj) { return service.salvar(obj); }

    @PutMapping("/{id}")
    public ResponseEntity<Resultado> atualizar(@PathVariable Integer id, @RequestBody Resultado novo) {
        try { return ResponseEntity.ok(service.atualizar(id, novo)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

