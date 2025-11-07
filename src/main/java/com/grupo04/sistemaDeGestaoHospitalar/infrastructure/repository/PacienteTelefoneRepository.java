package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.TelefonePaciente;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.TelefonePacienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteTelefoneRepository extends JpaRepository<TelefonePaciente, TelefonePacienteId> {
}
