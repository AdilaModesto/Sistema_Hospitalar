package com.grupo04.sistemaDeGestaoHospitalar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class Admin {

    @GetMapping("/admin/monitoramento")
    public String dashboardMonitoramento() {
        return "monitoramento"; // sem .html
    }
}
