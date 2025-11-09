package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "medico")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private Long idMedico;

    @ManyToOne
    @JoinColumn(name = "id_admin", nullable = false)
    private Administrador administrador;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 100)
    private String apelido;

    @Column(length = 20)
    private String sexo;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(length = 100)
    private String distrito;

    @Column(length = 100)
    private String bairro;

    @Column(length = 150)
    private String rua;

    @OneToMany(mappedBy = "medico")
    private Set<MedicoEspecialidade> especialidades;

    @OneToMany(mappedBy = "medico")
    private Set<MedicoDisponibilidade> disponibilidades;

    @OneToMany(mappedBy = "medico")
    private Set<Marcacao> marcacoes;

    @OneToMany(mappedBy = "medico")
    private Set<Urgencia> urgencias;

    @OneToMany(mappedBy = "medico")
    private Set<Exame> exames;
}