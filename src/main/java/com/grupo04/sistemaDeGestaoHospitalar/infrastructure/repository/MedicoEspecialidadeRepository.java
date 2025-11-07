package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.MedicoEspecialidade;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.MedicoEspecialidadeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoEspecialidadeRepository extends JpaRepository<MedicoEspecialidade, MedicoEspecialidadeId> {
}
