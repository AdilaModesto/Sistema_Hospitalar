package com.grupo04.sistemaDeGestaoHospitalar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class medico {
    

 

    @GetMapping("/perfil")
    public String mostrarAlterarDados() {
        return "alterarDados"; // o nome do arquivo HTML
    }
}
