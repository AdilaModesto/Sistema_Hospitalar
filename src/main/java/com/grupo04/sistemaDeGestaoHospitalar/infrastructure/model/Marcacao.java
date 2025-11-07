package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Marcacao")
public class Marcacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMarcacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico medico;

    @Column(length = 20, nullable = false)
    private String estado;

    @Column(nullable = false)
    private LocalTime hora;

    @Column(nullable = false)
    private LocalDate data;
}
