package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Resultado;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Integer> {
}

