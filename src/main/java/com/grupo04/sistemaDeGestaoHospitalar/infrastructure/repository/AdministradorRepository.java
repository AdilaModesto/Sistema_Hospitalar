package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
}
