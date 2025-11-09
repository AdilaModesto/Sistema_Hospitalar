package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Conta total de pacientes
    long count();

    // Conta pacientes por sexo
    @Query("SELECT p.sexo as genero, COUNT(p) as quantidade FROM Paciente p GROUP BY p.sexo")
    List<Object[]> countBySexo();

    // Busca pacientes cadastrados hoje
    @Query(value = "SELECT COUNT(*) FROM paciente WHERE data_nasc = CURRENT_DATE", nativeQuery = true)
    long countPacientesHoje();

    // Outros métodos personalizados que você precisar...
}