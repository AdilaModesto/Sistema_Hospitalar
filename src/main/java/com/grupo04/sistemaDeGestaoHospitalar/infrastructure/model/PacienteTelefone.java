package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "paciente_telefone")
@IdClass(PacienteTelefoneId.class)
public class PacienteTelefone {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @Id
    @Column(name = "telefone", length = 20, nullable = false)
    private String telefone;
}