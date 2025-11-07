package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoEspecialidadeId implements java.io.Serializable {
    private Integer idMedico;
    private String especialidade;
}
