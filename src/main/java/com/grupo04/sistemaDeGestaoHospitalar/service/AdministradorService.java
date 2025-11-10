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

    public Optional<Administrador> buscar(String email) {
        return repo.findByEmail(email);
    }

    public Administrador salvar(Administrador obj) {
        return repo.save(obj);
    }

    public Administrador atualizar(String email, Administrador novo) {
    return repo.findByEmail(email).map(a -> {
        if (novo.getNome() != null) a.setNome(novo.getNome());
        if (novo.getEmail() != null) a.setEmail(novo.getEmail());
        return repo.save(a);
    }).orElseThrow(() -> new RuntimeException("Administrador não encontrado com o email: " + email));
}

    public void deletar(String email) {
        repo.deleteByEmail(email);
    }
}
