package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Marcacao;

@Repository
public interface MarcacaoRepository extends JpaRepository<Marcacao, Integer> {
}

