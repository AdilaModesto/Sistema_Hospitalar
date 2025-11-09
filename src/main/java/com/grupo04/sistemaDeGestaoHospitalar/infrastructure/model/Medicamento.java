package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "medicamento")
@IdClass(MedicamentoId.class)
public class Medicamento {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_receita", nullable = false)
    private Receita receita;

    @Id
    @Column(name = "medicamento", length = 150, nullable = false)
    private String nome;

    @Column(nullable = false, length = 100)
    private String dosagem;
}