package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "consulta")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private Long idConsulta;

    @OneToOne
    @JoinColumn(name = "id_marcacao", nullable = false, unique = true)
    private Marcacao marcacao;

    @Column(name = "tipo_consulta", nullable = false, length = 100)
    private String tipoConsulta;

    private String diagnostico;

    @OneToMany(mappedBy = "consulta")
    private Set<Exame> exames;

    @OneToMany(mappedBy = "consulta")
    private Set<Receita> receitas;
}