package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "exame")
public class Exame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exame")
    private Long idExame;

    @ManyToOne
    @JoinColumn(name = "id_consulta", nullable = false)
    private Consulta consulta;

    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico medico;

    @Column(name = "tipo_exame", nullable = false, length = 100)
    private String tipoExame;

    @Column(name = "data_realizacao")
    private LocalDate dataRealizacao;

    @OneToOne(mappedBy = "exame", cascade = CascadeType.ALL)
    private Resultado resultado;
}