package com.grupo04.sistemaDeGestaoHospitalar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class Admin {
@GetMapping("/admin/paciente/cadastrar")
    public String cadastrarPaciente() {
        return "cadastroPacienteAdmin";
    }

    @GetMapping("/admin/paciente/editar")
    public String editarPaciente() {
        return "alterarDadosPeloAdmim";
    }

    @GetMapping("/admin/medico/cadastrar")
    public String cadastrarMedico() {
        return "cadastroMedico";
    }

    @GetMapping("/admin/monitoramento")
    public String dashboardMonitoramento() {
        return "monitoramento"; // sem .html
    }

    @GetMapping("/admin/urgencias")
    public String historicoUrgencias() {
        return "urgencias";
    }

    @GetMapping("/admin/perfil")
    public String meuPerfil() {
        return "alterarDados";
    }
}
