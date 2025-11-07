package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // para getters e setters
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Administrador")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin")
    private Long idAdmin;

    @NotBlank
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "apelido")
    private String apelido;

    @NotBlank
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(name = "senha", nullable = false)
    private String senha;

    
}