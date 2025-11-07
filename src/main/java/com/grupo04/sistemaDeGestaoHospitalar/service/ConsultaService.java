package com.grupo04.sistemaDeGestaoHospitalar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Consulta;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository.ConsultaRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultaService {
    private final ConsultaRepository repo;

    public List<Consulta> listar() { return repo.findAll(); }
    public Optional<Consulta> buscar(Integer id) { return repo.findById(id); }
    public Consulta salvar(Consulta obj) { return repo.save(obj); }
    public Consulta atualizar(Integer id, Consulta novo) {
        return repo.findById(id).map(c -> {
            c.setTipoConsulta(novo.getTipoConsulta());
            c.setDiagnostico(novo.getDiagnostico());
            return repo.save(c);
        }).orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
    }
    public void deletar(Integer id) { repo.deleteById(id); }
}

