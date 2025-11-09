package com.grupo04.sistemaDeGestaoHospitalar.dto;

import lombok.Data;
import java.util.List;

@Data
public class DashboardDTO {
    private long totalPacientes;
    private long marcacoesHoje;
    private double taxaCancelamento;

    private List<GeneroDTO> dadosGenero;
    private List<EspecialidadeDTO> dadosEspecialidade;
    private List<MarcacaoMensalDTO> dadosMarcacoes;
    private List<CancelamentoMensalDTO> dadosCancelamento;
    private List<UrgenciaDTO> ultimasUrgencias;
    private List<MedicoAtivoDTO> medicosAtivos;

    @Data
    public static class GeneroDTO {
        private String genero;
        private long quantidade;
    }

    @Data
    public static class EspecialidadeDTO {
        private String especialidade;
        private long quantidade;
    }

    @Data
    public static class MarcacaoMensalDTO {
        private String mes;
        private long consultas;
        private long exames;
        private long urgencias;
    }

    @Data
    public static class CancelamentoMensalDTO {
        private String mes;
        private double taxa;
    }

    @Data
    public static class UrgenciaDTO {
        private String paciente;
        private String prioridade;
        private String data;
    }

    @Data
    public static class MedicoAtivoDTO {
        private String nome;
        private long consultas;
    }
}