package com.grupo04.sistemaDeGestaoHospitalar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Receita;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository.ReceitaRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceitaService {
    private final ReceitaRepository repo;

    public List<Receita> listar() { return repo.findAll(); }
    public Optional<Receita> buscar(Integer id) { return repo.findById(id); }
    public Receita salvar(Receita obj) { return repo.save(obj); }
    public void deletar(Integer id) { repo.deleteById(id); }
}

