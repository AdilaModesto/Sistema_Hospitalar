package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Medico_Especialidade")
public class MedicoEspecialidade {

    @EmbeddedId
    private MedicoEspecialidadeId id;

    @MapsId("idMedico")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medico", nullable = false, foreignKey = @ForeignKey(name = "fk_medico_especialidade"))
    private Medico medico;
}
