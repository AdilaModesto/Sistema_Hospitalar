package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class MedicamentoId implements Serializable {
    private Receita receita;
    private String nome;
}