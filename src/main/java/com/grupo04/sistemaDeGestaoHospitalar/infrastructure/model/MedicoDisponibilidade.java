package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "medico_disponibilidade")
@IdClass(MedicoDisponibilidadeId.class)
public class MedicoDisponibilidade {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico medico;

    @Id
    @Column(name = "dia_semana", length = 15, nullable = false)
    private String diaSemana;

    @Id
    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fim", nullable = false)
    private LocalTime horaFim;
}

@Data
class MedicoDisponibilidadeId implements Serializable {
    private Long medico;
    private String diaSemana;
    private LocalTime horaInicio;
}