package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConsulta;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marcacao", nullable = false, unique = true)
    private Marcacao marcacao;

    @Column(name = "tipo_consulta", length = 100, nullable = false)
    private String tipoConsulta;

    @Column(columnDefinition = "TEXT")
    private String diagnostico;
}
