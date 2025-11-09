package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Long idPaciente;

    @ManyToOne
    @JoinColumn(name = "id_admin", nullable = false)
    private Administrador administrador;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 100)
    private String apelido;

    @Column(length = 20)
    private String sexo;

    @Column(name = "data_nasc", nullable = false)
    private LocalDate dataNasc;

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

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private Set<PacienteTelefone> telefones;

    @OneToMany(mappedBy = "paciente")
    private Set<Marcacao> marcacoes;

    @OneToMany(mappedBy = "paciente")
    private Set<Urgencia> urgencias;
}