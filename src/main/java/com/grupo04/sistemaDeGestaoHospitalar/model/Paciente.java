package com.grupo04.sistemaDeGestaoHospitalar.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "paciente")
@Data //Faz getters e setters
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_paciente;

    private String nome;
    private String apelido;
    private String email;
    private String telefone;

    
}
