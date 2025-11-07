package com.grupo04.sistemaDeGestaoHospitalar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Marcacao;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository.MarcacaoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarcacaoService {
    private final MarcacaoRepository repo;

    public List<Marcacao> listar() { return repo.findAll(); }
    public Optional<Marcacao> buscar(Integer id) { return repo.findById(id); }
    public Marcacao salvar(Marcacao obj) { return repo.save(obj); }
    public Marcacao atualizar(Integer id, Marcacao novo) {
        return repo.findById(id).map(m -> {
            m.setEstado(novo.getEstado());
            m.setData(novo.getData());
            m.setHora(novo.getHora());
            return repo.save(m);
        }).orElseThrow(() -> new RuntimeException("Marcação não encontrada"));
    }
    public void deletar(Integer id) { repo.deleteById(id); }
}

