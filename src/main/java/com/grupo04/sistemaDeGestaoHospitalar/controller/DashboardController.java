package com.grupo04.sistemaDeGestaoHospitalar.controller;

import com.grupo04.sistemaDeGestaoHospitalar.dto.DashboardDTO;
import com.grupo04.sistemaDeGestaoHospitalar.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboardAdmin")
    public String dashboardAdmin(Model model) {
        return "monitoramento";
    }

    @GetMapping("/api/dashboard-data")
    @ResponseBody
    public DashboardDTO getDashboardData() {
        return dashboardService.getDashboardData();
    }
}
