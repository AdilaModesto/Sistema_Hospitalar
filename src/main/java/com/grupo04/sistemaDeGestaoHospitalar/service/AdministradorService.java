package com.grupo04.sistemaDeGestaoHospitalar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Administrador;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository.AdministradorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministradorService {
    private final AdministradorRepository repo;

    public List<Administrador> listar() {
        return repo.findAll();
    }

    public Optional<Administrador> buscar(Integer id) {
        return repo.findById(id);
    }

    public Administrador salvar(Administrador obj) {
        return repo.save(obj);
    }

    public Administrador atualizar(Integer id, Administrador novo) {
        return repo.findById(id).map(a -> {
            a.setNome(novo.getNome());
            a.setEmail(novo.getEmail());
            return repo.save(a);
        }).orElseThrow(() -> new RuntimeException("Administrador não encontrado"));
    }

    public void deletar(Integer id) {
        repo.deleteById(id);
    }
}
