package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelefonePacienteId implements java.io.Serializable {
private Integer idPaciente;
    private String telefone;
}
