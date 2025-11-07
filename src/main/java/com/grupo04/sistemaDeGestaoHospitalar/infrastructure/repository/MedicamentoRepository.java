package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Medicamento;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.MedicamentoId;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, MedicamentoId> {
}

