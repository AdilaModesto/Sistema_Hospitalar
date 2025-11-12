package com.grupo04.sistemaDeGestaoHospitalar.controller;

import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Administrador;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Medico;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Paciente;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository.AdministradorRepository;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository.MedicoRepository;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping("/perform-login")
    public String performLogin(@RequestParam String email,
            @RequestParam String senha,
            @RequestParam String perfil,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        perfil = perfil == null ? "" : perfil.toUpperCase();

        if ("ADMIN".equals(perfil)) {
            Optional<Administrador> opt = administradorRepository.findByEmail(email);
            if (opt.isPresent() && opt.get().getSenha().equals(senha)) {
                Administrador admin = opt.get();
                session.setAttribute("userId", admin.getIdAdmin());
                session.setAttribute("userName", admin.getNome());
                session.setAttribute("role", "ADMIN");
                return "redirect:/dashboardAdmin";
            }
        } /***else if ("PACIENTE".equals(perfil)) {
            Optional<Paciente> opt = pacienteRepository.findByEmail(email);
            if (opt.isPresent() && opt.get().getSenha().equals(senha)) {
                Paciente paciente = opt.get();
                session.setAttribute("userId", paciente.getIdPaciente());
                session.setAttribute("userName", paciente.getNome());
                session.setAttribute("role", "PACIENTE");
                return "redirect:/dashboardPaciente";
            }
        } else if ("MEDICO".equals(perfil)) {
            Optional<Medico> opt = medicoRepository.findByEmail(email);
            if (opt.isPresent() && opt.get().getSenha().equals(senha)) {
                Medico medico = opt.get();
                session.setAttribute("userId", medico.getIdMedico());
                session.setAttribute("userName", medico.getNome());
                session.setAttribute("role", "MEDICO");
                return "redirect:/dashboardMedico";
            }
        }***/

        // falha de autenticação
        redirectAttributes.addFlashAttribute("loginError", "Credenciais inválidas");
        return "redirect:/login";
    }
}
