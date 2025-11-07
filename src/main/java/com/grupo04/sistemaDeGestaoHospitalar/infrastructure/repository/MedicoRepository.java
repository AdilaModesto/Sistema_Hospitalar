package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
}
