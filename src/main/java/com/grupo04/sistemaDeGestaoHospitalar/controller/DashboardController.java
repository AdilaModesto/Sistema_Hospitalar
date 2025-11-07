package com.grupo04.sistemaDeGestaoHospitalar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboardPaciente")
    public String dashboardPaciente() {
        return "dashboardPaciente"; // templates/dashboardPaciente.html
    }

    @GetMapping("/dashboardMedico")
    public String dashboardMedico() {
        return "dashboardMedico"; // templates/dashboardMedico.html
    }

    @GetMapping("/dashboardAdmin")
    public String dashboardAdmin() {
        return "monitoramento"; // templates/dashboardAdmin.html
    }
}

    

