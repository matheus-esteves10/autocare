package org.example.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendamentoDto(Long id,
                             LocalDate dataAgendamento,
                             LocalTime horaDoAgendamento,
                             String descricaoServico,
                             Long idOficina,
                             Long idPessoa) {
}
