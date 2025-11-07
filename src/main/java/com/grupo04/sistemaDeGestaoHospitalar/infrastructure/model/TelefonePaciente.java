package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Paciente_Telefone")
public class TelefonePaciente {
@EmbeddedId
    private TelefonePacienteId id;

    @MapsId("idPaciente")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente", nullable = false, foreignKey = @ForeignKey(name = "fk_paciente_telefone"))
    private Paciente paciente;
}
