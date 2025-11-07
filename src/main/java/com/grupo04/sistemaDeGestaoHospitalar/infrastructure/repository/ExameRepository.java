package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Exame;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Integer> {
}

