package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Telefone_Admin")
public class TelefoneAdmin {

    @EmbeddedId
    private TelefoneAdminId id;

    @MapsId("idAdmin")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_admin", nullable = false, foreignKey = @ForeignKey(name = "fk_telefone_admin"))
    private Administrador administrador;
}