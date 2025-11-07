package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.MedicoDisponibilidade;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.MedicoDisponibilidadeId;

@Repository
public interface MedicoDisponibilidadeRepository extends JpaRepository<MedicoDisponibilidade, MedicoDisponibilidadeId> {
}

