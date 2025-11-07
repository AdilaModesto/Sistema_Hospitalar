package com.grupo04.sistemaDeGestaoHospitalar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Paciente;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository.PacienteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteRepository repo;

    public List<Paciente> listar() {
        return repo.findAll();
    }

    public Optional<Paciente> buscar(Long id) {
        return repo.findById(id);
    }

    public Paciente salvar(Paciente obj) {
        return repo.save(obj);
    }

    public Paciente atualizar(Long id, Paciente novo) {
        return repo.findById(id).map(p -> {
            p.setNome(novo.getNome());
            p.setSexo(novo.getSexo());
            return repo.save(p);
        }).orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
    }

    public void deletar(Long id) {
        repo.deleteById(id);
    }
}


