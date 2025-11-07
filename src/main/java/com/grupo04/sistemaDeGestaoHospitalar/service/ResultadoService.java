package com.grupo04.sistemaDeGestaoHospitalar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Resultado;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository.ResultadoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResultadoService {
    private final ResultadoRepository repo;

    public List<Resultado> listar() { return repo.findAll(); }
    public Optional<Resultado> buscar(Integer id) { return repo.findById(id); }
    public Resultado salvar(Resultado obj) { return repo.save(obj); }
    public Resultado atualizar(Integer id, Resultado novo) {
        return repo.findById(id).map(r -> {
            r.setResultado(novo.getResultado());
            r.setDiagnostico(novo.getDiagnostico());
            r.setDoenca(novo.getDoenca());
            return repo.save(r);
        }).orElseThrow(() -> new RuntimeException("Resultado não encontrado"));
    }
    public void deletar(Integer id) { repo.deleteById(id); }
}

