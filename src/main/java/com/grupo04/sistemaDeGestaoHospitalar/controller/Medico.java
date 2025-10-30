package com.grupo04.sistemaDeGestaoHospitalar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class Medico {
@GetMapping("/medico/dashboard")
    public String dashboard() {
        return "dashboardMedico";
    }

    @GetMapping("/medico/consulta/detalhes")
    public String detalhesConsulta() {
        return "detalheConsultaMedico";
    }

    @GetMapping("/medico/marcacoes/pendentes")
    public String confirmarMarcacoes() {
        return "confirmacaoMarcacao";
    }

    @GetMapping("/medico/exame/resultado")
    public String registrarResultado() {
        return "resultadoExame";
    }

    @GetMapping("/medico/perfil")
    public String meuPerfil() {
        return "alterarDados";
    }
}
