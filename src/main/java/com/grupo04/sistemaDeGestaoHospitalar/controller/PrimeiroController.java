package com.grupo04.sistemaDeGestaoHospitalar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller

public class PrimeiroController {

    @GetMapping("/")
    public String home() {
        return "index";

    }

     @GetMapping("/login")
    public String login() {
        return "login"; 
    }

     @GetMapping("/paciente/cadastro")
    public String cadastroPaciente() {
        return "cadastroPaciente";
    }
    

}
