package com.grupo04.sistemaDeGestaoHospitalar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Exame;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository.ExameRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExameService {
    private final ExameRepository repo;

    public List<Exame> listar() { return repo.findAll(); }
    public Optional<Exame> buscar(Integer id) { return repo.findById(id); }
    public Exame salvar(Exame obj) { return repo.save(obj); }
    public Exame atualizar(Integer id, Exame novo) {
        return repo.findById(id).map(e -> {
            e.setTipoExame(novo.getTipoExame());
            e.setDataRealizacao(novo.getDataRealizacao());
            return repo.save(e);
        }).orElseThrow(() -> new RuntimeException("Exame não encontrado"));
    }
    public void deletar(Integer id) { repo.deleteById(id); }
}

