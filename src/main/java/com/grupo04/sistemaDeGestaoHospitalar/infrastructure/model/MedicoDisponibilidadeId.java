package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import java.time.LocalTime;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDisponibilidadeId implements java.io.Serializable {
    private Integer idMedico;
    private String diaSemana;
    private LocalTime horaInicio;
}
