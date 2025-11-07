package com.grupo04.sistemaDeGestaoHospitalar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Urgencia;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository.UrgenciaRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrgenciaService {
    private final UrgenciaRepository repo;

    public List<Urgencia> listar() { return repo.findAll(); }
    public Optional<Urgencia> buscar(Integer id) { return repo.findById(id); }
    public Urgencia salvar(Urgencia obj) { return repo.save(obj); }
    public Urgencia atualizar(Integer id, Urgencia novo) {
        return repo.findById(id).map(u -> {
            u.setPrioridade(novo.getPrioridade());
            u.setDiagnosticoIn(novo.getDiagnosticoIn());
            return repo.save(u);
        }).orElseThrow(() -> new RuntimeException("Urgência não encontrada"));
    }
    public void deletar(Integer id) { repo.deleteById(id); }
}

