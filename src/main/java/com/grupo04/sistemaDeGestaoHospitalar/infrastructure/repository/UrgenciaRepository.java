package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Urgencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UrgenciaRepository extends JpaRepository<Urgencia, Long> {
    // Busca últimas urgências com prioridade alta
    @Query("SELECT u FROM Urgencia u WHERE u.prioridade = 'Alta' ORDER BY u.dataHora DESC")
    List<Urgencia> getUltimasUrgenciasAlta();

    // Conta urgências por prioridade
    @Query("SELECT u.prioridade, COUNT(u) FROM Urgencia u GROUP BY u.prioridade")
    List<Object[]> countByPrioridade();
}