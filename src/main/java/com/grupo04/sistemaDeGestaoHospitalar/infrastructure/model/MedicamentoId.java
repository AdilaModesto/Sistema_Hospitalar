package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentoId implements java.io.Serializable {
    private Integer idReceita;
    private String medicamento;
}
