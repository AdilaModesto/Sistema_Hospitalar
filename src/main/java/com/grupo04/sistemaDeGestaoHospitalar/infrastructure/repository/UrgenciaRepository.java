package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Urgencia;

@Repository
public interface UrgenciaRepository extends JpaRepository<Urgencia, Integer> {
}

