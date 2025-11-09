package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "medico_especialidade")
@IdClass(MedicoEspecialidadeId.class)
public class MedicoEspecialidade {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico medico;

    @Id
    @Column(name = "especialidade", length = 100, nullable = false)
    private String especialidade;
}

@Data
class MedicoEspecialidadeId implements Serializable {
    private Long medico;
    private String especialidade;
}