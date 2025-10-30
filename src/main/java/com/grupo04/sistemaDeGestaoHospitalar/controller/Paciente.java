package com.grupo04.sistemaDeGestaoHospitalar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class Paciente {

    @GetMapping("/paciente/dashboard")
    public String dashboard() {
        return "dashboardPaciente";
    }

    @GetMapping("/paciente/marcacao")
    public String agendarMarcacao() {
        return "marcacao";
    }

    @GetMapping("/paciente/receitas")
    public String minhasReceitas() {
        return "receita";
    }

    @GetMapping("/paciente/alterarDados")
    public String meuPerfil() {
        return "alterarDados";
    }
}
