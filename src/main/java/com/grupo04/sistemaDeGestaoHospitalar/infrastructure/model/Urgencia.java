package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Urgencia")
public class Urgencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUrgencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico medico;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora = LocalDateTime.now();

    @Column(length = 20)
    private String prioridade;

    @Column(name = "diagnostico_in", columnDefinition = "TEXT")
    private String diagnosticoIn;
}
