package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.TelefoneAdmin;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.TelefoneAdminId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneAdminRepository extends JpaRepository<TelefoneAdmin, TelefoneAdminId> {
}
