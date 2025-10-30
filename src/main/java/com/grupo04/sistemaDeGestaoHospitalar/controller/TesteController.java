package com.grupo04.sistemaDeGestaoHospitalar.controller;

import java.util.*;
import com.grupo04.sistemaDeGestaoHospitalar.repository.PacienteRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/teste")
public class TesteController {
    
    @Autowired
    private final PacienteRepository pacienteRepository;

    public TesteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

 
}
