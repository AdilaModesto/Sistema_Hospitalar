package com.grupo04.sistemaDeGestaoHospitalar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Medico;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository.MedicoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicoService {
    private final MedicoRepository repo;

    public List<Medico> listar() { return repo.findAll(); }
    public Optional<Medico> buscar(Integer id) { return repo.findById(id); }
    public Medico salvar(Medico obj) { return repo.save(obj); }
    public Medico atualizar(Integer id, Medico novo) {
        return repo.findById(id).map(m -> {
            m.setNome(novo.getNome());
            m.setApelido(novo.getApelido());
            m.setSexo(novo.getSexo());
            m.setEmail(novo.getEmail());
            return repo.save(m);
        }).orElseThrow(() -> new RuntimeException("Médico não encontrado"));
    }
    public void deletar(Integer id) { repo.deleteById(id); }
}

