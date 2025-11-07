package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Long idPaciente;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_admin", nullable = false, foreignKey = @ForeignKey(name = "fk_paciente_administrador"))
    private Administrador administrador;

    @NotBlank
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "apelido", length = 100)
    private String apelido;

    @Column(name = "sexo", length = 20)
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @NotNull
    @Past
    @Column(name = "data_nasc", nullable = false)
    private LocalDate dataNasc;

    @NotBlank
    @Email
    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(name = "senha", length = 255, nullable = false)
    private String senha;

    @Column(name = "distrito", length = 100)
    private String distrito;

    @Column(name = "bairro", length = 100)
    private String bairro;

    @Column(name = "rua", length = 150)
    private String rua;
}
