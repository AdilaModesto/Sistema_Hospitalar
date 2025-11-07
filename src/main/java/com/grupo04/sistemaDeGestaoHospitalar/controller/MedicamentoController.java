package com.grupo04.sistemaDeGestaoHospitalar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Medicamento;
import com.grupo04.sistemaDeGestaoHospitalar.service.MedicamentoService;

import java.util.List;

@RestController
@RequestMapping("/medicamentos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MedicamentoController {

    private final MedicamentoService service;

    @GetMapping
    public List<Medicamento> listar() { return service.listar(); }

    @PostMapping
    public Medicamento salvar(@RequestBody Medicamento obj) { return service.salvar(obj); }

    @DeleteMapping("/{idReceita}/{nome}")
    public void deletar(@PathVariable Integer idReceita, @PathVariable String nome) {
        service.deletar(idReceita, nome);
    }
}

