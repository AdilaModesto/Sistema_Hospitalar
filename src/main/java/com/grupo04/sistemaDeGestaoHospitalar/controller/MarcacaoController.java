package com.grupo04.sistemaDeGestaoHospitalar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Marcacao;
import com.grupo04.sistemaDeGestaoHospitalar.service.MarcacaoService;

import java.util.List;

@RestController
@RequestMapping("/marcacoes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MarcacaoController {

    private final MarcacaoService service;

    @GetMapping
    public List<Marcacao> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ResponseEntity<Marcacao> buscar(@PathVariable Integer id) {
        return service.buscar(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Marcacao salvar(@RequestBody Marcacao obj) { return service.salvar(obj); }

    @PutMapping("/{id}")
    public ResponseEntity<Marcacao> atualizar(@PathVariable Integer id, @RequestBody Marcacao novo) {
        try { return ResponseEntity.ok(service.atualizar(id, novo)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

