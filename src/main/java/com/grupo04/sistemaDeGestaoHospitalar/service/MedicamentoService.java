package com.grupo04.sistemaDeGestaoHospitalar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Medicamento;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository.MedicamentoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicamentoService {
    private final MedicamentoRepository repo;

    public List<Medicamento> listar() { return repo.findAll(); }
    public Medicamento salvar(Medicamento obj) { return repo.save(obj); }
    public void deletar(Integer idReceita, String medicamento) {
        repo.deleteById(new com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.MedicamentoId(idReceita, medicamento));
    }
}

