package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Medicamento")
public class Medicamento {

    @EmbeddedId
    private MedicamentoId id;

    @MapsId("idReceita")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receita", nullable = false)
    private Receita receita;

    @Column(length = 100, nullable = false)
    private String dosagem;
}
