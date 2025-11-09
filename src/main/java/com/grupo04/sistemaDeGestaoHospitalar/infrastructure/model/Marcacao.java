package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "marcacao")
public class Marcacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marcacao")
    private Long idMarcacao;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico medico;

    @Column(nullable = false)
    private String estado = "Agendada";

    @Column(nullable = false)
    private LocalTime hora;

    @Column(nullable = false)
    private LocalDate data;

    @OneToOne(mappedBy = "marcacao", cascade = CascadeType.ALL)
    private Consulta consulta;
}