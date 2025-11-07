package com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneAdminId implements java.io.Serializable {
    private Integer idAdmin;
    private String telefone;
}
