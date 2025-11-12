package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Marcacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MarcacaoRepository extends JpaRepository<Marcacao, Long> {
    // Conta marcações para hoje
    @Query("SELECT COUNT(m) FROM Marcacao m WHERE m.data = CURRENT_DATE")
    long countMarcacoesHoje();

    // Taxa de cancelamento
    @Query("SELECT COUNT(m) * 100.0 / (SELECT COUNT(m2) FROM Marcacao m2) FROM Marcacao m WHERE m.estado = 'Realizada'")
    double getTaxaMarcacoesRealizadas();

    // Marcações por tipo nos últimos 6 meses
    @Query("SELECT m.data as mes, COUNT(m) as quantidade, c.tipoConsulta as tipo " +
            "FROM Marcacao m JOIN m.consulta c " +
            "WHERE m.data >= ?1 " +
            "GROUP BY m.data, c.tipoConsulta " +
            "ORDER BY m.data")
    List<Object[]> getMarcacoesPorTipo(LocalDate dataInicial);

    // Marcações mensais por tipo (ano, mês, tipo, quantidade)
    @Query("SELECT YEAR(m.data) as ano, MONTH(m.data) as mes, c.tipoConsulta as tipo, COUNT(m) as quantidade " +
            "FROM Marcacao m LEFT JOIN m.consulta c " +
            "WHERE m.data >= ?1 " +
            "GROUP BY YEAR(m.data), MONTH(m.data), c.tipoConsulta " +
            "ORDER BY YEAR(m.data), MONTH(m.data)")
    List<Object[]> getMarcacoesPorTipoMensal(LocalDate dataInicial);

    // Taxa de Marcaoes Realizadas por mês (ano, mês, taxaPercentual)
    @Query("SELECT YEAR(m.data) as ano, MONTH(m.data) as mes, (SUM(CASE WHEN m.estado = 'Realizada' THEN 1 ELSE 0 END) * 100.0) / COUNT(m) as taxa "
            +
            "FROM Marcacao m " +
            "WHERE m.data >= ?1 " +
            "GROUP BY YEAR(m.data), MONTH(m.data) " +
            "ORDER BY YEAR(m.data), MONTH(m.data)")
    List<Object[]> getTaxaMarcacoesRealizadas(LocalDate dataInicial);

    // Médicos mais ativos (com mais consultas)
    @Query("SELECT m.medico.nome as medico, COUNT(m) as quantidade " +
            "FROM Marcacao m " +
            "WHERE m.estado = 'Realizada' " +
            "GROUP BY m.medico.nome " +
            "ORDER BY COUNT(m) DESC")
    List<Object[]> getMedicosMaisAtivos();
}