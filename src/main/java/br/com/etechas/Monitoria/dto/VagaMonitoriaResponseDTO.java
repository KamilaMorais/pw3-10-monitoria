package br.com.etechas.Monitoria.dto;

public record VagaMonitoriaResponseDTO(
        Long id,
        String disciplina,
        String nomeMonitor,
        Integer vagasTotais,
        Integer vagasOcupadas,
        Boolean aberta
) { }

