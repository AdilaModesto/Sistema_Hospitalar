package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.MedicoEspecialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicoEspecialidadeRepository extends JpaRepository<MedicoEspecialidade, Long> {
    // Conta consultas por especialidade
    @Query("SELECT me.especialidade as especialidade, COUNT(m) as quantidade " +
            "FROM MedicoEspecialidade me " +
            "LEFT JOIN me.medico.marcacoes m " +
            "GROUP BY me.especialidade " +
            "ORDER BY COUNT(m) DESC")
    List<Object[]> getEspecialidadesMaisDemandadas();
}