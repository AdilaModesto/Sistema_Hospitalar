package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "resultado")
public class Resultado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultado")
    private Long idResultado;

    @OneToOne
    @JoinColumn(name = "id_exame", nullable = false, unique = true)
    private Exame exame;

    private String resultado;
    private String diagnostico;

    @Column(length = 150)
    private String doenca;
}