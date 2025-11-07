package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Integer> {
}
