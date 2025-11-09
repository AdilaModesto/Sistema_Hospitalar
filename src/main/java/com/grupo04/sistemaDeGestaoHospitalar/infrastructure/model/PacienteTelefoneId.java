package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class PacienteTelefoneId implements Serializable {
    private Long paciente;
    private String telefone;
}