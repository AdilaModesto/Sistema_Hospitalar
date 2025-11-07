package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Resultado")
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idResultado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_exame", nullable = false, unique = true)
    private Exame exame;

    @Column(columnDefinition = "TEXT")
    private String resultado;

    @Column(columnDefinition = "TEXT")
    private String diagnostico;

    @Column(length = 150)
    private String doenca;
}
