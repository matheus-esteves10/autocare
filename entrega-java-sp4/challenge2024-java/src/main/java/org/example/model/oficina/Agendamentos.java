package org.example.model.oficina;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Agendamentos {
    private Long id;
    private LocalDate dataAgendamento;
    private LocalTime horaDoAgendamento;
    private String descricaoServico;
    private Long idOficina;
    private Long idPessoa;

    public Agendamentos(Long id, LocalDate dataAgendamento, LocalTime horaDoAgendamento, String descricaoServico, Long idOficina, Long idPessoa) {
        this.id = id;
        this.dataAgendamento = dataAgendamento;
        this.horaDoAgendamento = horaDoAgendamento;
        this.descricaoServico = descricaoServico;
        this.idOficina = idOficina;
        this.idPessoa = idPessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public LocalTime getHoraDoAgendamento() {
        return horaDoAgendamento;
    }

    public void setHoraDoAgendamento(LocalTime horaDoAgendamento) {
        this.horaDoAgendamento = horaDoAgendamento;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public Long getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(Long idOficina) {
        this.idOficina = idOficina;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

}
