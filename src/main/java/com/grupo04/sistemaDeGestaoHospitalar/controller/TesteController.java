package com.grupo04.sistemaDeGestaoHospitalar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository.AdministradorRepository;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/teste")
public class TesteController {
    
    public TesteController(AdministradorRepository pacienteRepository) {
        
    }

}
