package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Medico_Disponibilidade")
public class MedicoDisponibilidade {

    @EmbeddedId
    private MedicoDisponibilidadeId id;

    @MapsId("idMedico")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico medico;

    @Column(name = "hora_fim", nullable = false)
    private LocalTime horaFim;
}
