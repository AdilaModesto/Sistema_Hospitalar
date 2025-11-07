package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private Integer idMedico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_admin", nullable = false, foreignKey = @ForeignKey(name = "fk_medico_admin"))
    private Administrador administrador;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 100)
    private String apelido;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Sexo sexo;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 255)
    private String senha;

    @Column(length = 100)
    private String distrito;

    @Column(length = 100)
    private String bairro;

    @Column(length = 150)
    private String rua;
}
