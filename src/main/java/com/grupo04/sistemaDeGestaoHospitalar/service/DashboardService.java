package com.grupo04.sistemaDeGestaoHospitalar.service;

import com.grupo04.sistemaDeGestaoHospitalar.dto.DashboardDTO;
import com.grupo04.sistemaDeGestaoHospitalar.dto.DashboardDTO.GeneroDTO;
import com.grupo04.sistemaDeGestaoHospitalar.dto.DashboardDTO.EspecialidadeDTO;
import com.grupo04.sistemaDeGestaoHospitalar.dto.DashboardDTO.MedicoAtivoDTO;
import com.grupo04.sistemaDeGestaoHospitalar.dto.DashboardDTO.UrgenciaDTO;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository.PacienteRepository;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository.MarcacaoRepository;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository.MedicoEspecialidadeRepository;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.repository.UrgenciaRepository;
import com.grupo04.sistemaDeGestaoHospitalar.infrastructure.model.Urgencia;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MarcacaoRepository marcacaoRepository;

    @Autowired
    private MedicoEspecialidadeRepository medicoEspecialidadeRepository;

    @Autowired
    private UrgenciaRepository urgenciaRepository;

    public DashboardDTO getDashboardData() {
        DashboardDTO dashboard = new DashboardDTO();

        // Totais simples
        dashboard.setTotalPacientes(pacienteRepository.count());
        dashboard.setMarcacoesHoje(marcacaoRepository.countMarcacoesHoje());
        dashboard.setTaxaMarcacoesRealizadas(marcacaoRepository.getTaxaMarcacoesRealizadas());

        // Dados por gênero
        List<Object[]> sexoRows = pacienteRepository.countBySexo();
        List<GeneroDTO> generoList = new ArrayList<>();
        if (sexoRows != null) {
            for (Object[] row : sexoRows) {
                String genero = row[0] != null ? row[0].toString() : "N/A";
                long qtd = 0L;
                if (row[1] instanceof Number)
                    qtd = ((Number) row[1]).longValue();
                GeneroDTO g = new GeneroDTO();
                g.setGenero(genero);
                g.setQuantidade(qtd);
                generoList.add(g);
            }
        }
        dashboard.setDadosGenero(generoList);

        // Especialidades mais demandadas
        List<Object[]> espRows = medicoEspecialidadeRepository.getEspecialidadesMaisDemandadas();
        List<EspecialidadeDTO> espList = new ArrayList<>();
        if (espRows != null) {
            for (Object[] row : espRows) {
                String nome = row[0] != null ? row[0].toString() : "N/A";
                long qtd = 0L;
                if (row[1] instanceof Number)
                    qtd = ((Number) row[1]).longValue();
                EspecialidadeDTO e = new EspecialidadeDTO();
                e.setEspecialidade(nome);
                e.setQuantidade(qtd);
                espList.add(e);
            }
        }
        dashboard.setDadosEspecialidade(espList);

        // Médicos mais ativos
        List<Object[]> medRows = marcacaoRepository.getMedicosMaisAtivos();
        List<MedicoAtivoDTO> medList = new ArrayList<>();
        if (medRows != null) {
            for (Object[] row : medRows) {
                String nome = row[0] != null ? row[0].toString() : "N/A";
                long qtd = 0L;
                if (row[1] instanceof Number)
                    qtd = ((Number) row[1]).longValue();
                MedicoAtivoDTO m = new MedicoAtivoDTO();
                m.setNome(nome);
                m.setConsultas(qtd);
                medList.add(m);
            }
        }
        dashboard.setMedicosAtivos(medList);

        // Últimas urgências
        List<Urgencia> todasUrgencias = urgenciaRepository.getUltimasUrgenciasAlta();
        List<Urgencia> urgencias = todasUrgencias.isEmpty() ? todasUrgencias
                : todasUrgencias.subList(0, Math.min(5, todasUrgencias.size()));
        List<UrgenciaDTO> urgList = new ArrayList<>();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        if (urgencias != null) {
            for (Urgencia u : urgencias) {
                UrgenciaDTO d = new UrgenciaDTO();
                d.setPaciente(u.getPaciente() != null ? u.getPaciente().getNome() : "N/A");
                d.setPrioridade(u.getPrioridade());
                d.setData(u.getDataHora() != null ? u.getDataHora().format(fmt) : "");
                urgList.add(d);
            }
        }
        dashboard.setUltimasUrgencias(urgList);

        // Observação: campos marcacoesMensais e cancelamentos mensais não implementados
        // aqui

        // --- Marcações mensais por tipo (últimos 6 meses) ---
        LocalDate dataInicial = LocalDate.now().minusMonths(5).withDayOfMonth(1);

        // preparar mapa ordenado dos últimos 6 meses
        Map<YearMonth, DashboardDTO.MarcacaoMensalDTO> mensalMap = new LinkedHashMap<>();
        for (int i = 0; i < 6; i++) {
            YearMonth ym = YearMonth.from(dataInicial.plusMonths(i));
            DashboardDTO.MarcacaoMensalDTO dto = new DashboardDTO.MarcacaoMensalDTO();
            dto.setMes(ym.getMonth().getDisplayName(java.time.format.TextStyle.SHORT, new Locale("pt", "BR")) + "/"
                    + ym.getYear());
            dto.setConsultas(0);
            dto.setExames(0);
            dto.setUrgencias(0);
            mensalMap.put(ym, dto);
        }

        List<Object[]> tipoRows = marcacaoRepository.getMarcacoesPorTipoMensal(dataInicial);
        if (tipoRows != null) {
            for (Object[] row : tipoRows) {
                if (row.length < 4)
                    continue;
                int ano = ((Number) row[0]).intValue();
                int mes = ((Number) row[1]).intValue();
                String tipo = row[2] != null ? row[2].toString().toUpperCase() : "";
                long qtd = 0L;
                if (row[3] instanceof Number)
                    qtd = ((Number) row[3]).longValue();

                YearMonth ym = YearMonth.of(ano, mes);
                DashboardDTO.MarcacaoMensalDTO dto = mensalMap.get(ym);
                if (dto == null) {
                    // caso venha algum mês fora do intervalo, criar um novo
                    dto = new DashboardDTO.MarcacaoMensalDTO();
                    dto.setMes(ym.getMonth().getDisplayName(java.time.format.TextStyle.SHORT, new Locale("pt", "BR"))
                            + "/" + ym.getYear());
                    dto.setConsultas(0);
                    dto.setExames(0);
                    dto.setUrgencias(0);
                    mensalMap.put(ym, dto);
                }

                if (tipo.contains("CONSULTA")) {
                    dto.setConsultas(dto.getConsultas() + qtd);
                } else if (tipo.contains("EXAME")) {
                    dto.setExames(dto.getExames() + qtd);
                } else if (tipo.contains("URG")) {
                    dto.setUrgencias(dto.getUrgencias() + qtd);
                } else {
                    // Se tipo for nulo (marcacao sem consulta) contar como consulta básica
                    dto.setConsultas(dto.getConsultas() + qtd);
                }
            }
        }

        List<DashboardDTO.MarcacaoMensalDTO> marcacoesMensais = new ArrayList<>(mensalMap.values());
        dashboard.setDadosMarcacoes(marcacoesMensais);

        // --- Taxa de cancelamento mensal ---
        List<Object[]> taxaRows = marcacaoRepository.getTaxaRealizadasMensal(dataInicial);
        List<DashboardDTO.RealizadasMensalDTO> taxaList = new ArrayList<>();
        if (taxaRows != null) {
            // transformar em mapa para buscar label do mês
            Map<YearMonth, Double> taxaMap = new LinkedHashMap<>();
            for (Object[] row : taxaRows) {
                if (row.length < 3)
                    continue;
                int ano = ((Number) row[0]).intValue();
                int mes = ((Number) row[1]).intValue();
                double taxa = row[2] instanceof Number ? ((Number) row[2]).doubleValue() : 0.0;
                taxaMap.put(YearMonth.of(ano, mes), taxa);
            }

            for (YearMonth ym : mensalMap.keySet()) {
                DashboardDTO.RealizadasMensalDTO c = new DashboardDTO.RealizadasMensalDTO();
                c.setMes(mensalMap.get(ym).getMes());
                Double taxa = taxaMap.getOrDefault(ym, 0.0);
                c.setTaxa(Math.round(taxa * 10.0) / 10.0); // arredondar 1 casa decimal
                taxaList.add(c);
            }
        } else {
            // preencher zeros se não houver dados
            for (DashboardDTO.MarcacaoMensalDTO m : marcacoesMensais) {
                DashboardDTO.RealizadasMensalDTO c = new DashboardDTO.RealizadasMensalDTO();
                c.setMes(m.getMes());
                c.setTaxa(0.0);
                taxaList.add(c);
            }
        }

        dashboard.setDadosRealizadas(taxaList);

        return dashboard;
    }
}