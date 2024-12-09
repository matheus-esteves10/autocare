package org.example.service.agendamento;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.model.oficina.Agendamentos;

import java.sql.SQLException;
import java.util.List;

public interface AgendamentoService {
    Agendamentos create(Agendamentos agendamento) throws UnsupportedServiceOperationException, SQLException, NotSavedException;

    List<Agendamentos> findAll();

    List<Agendamentos> findByIdPessoa(Long idPessoa) throws NotFoundException;

    List<Agendamentos> findByIdOficina(Long idOficina) throws NotFoundException;

    Agendamentos update(Agendamentos entity) throws NotFoundException, SQLException;

    void deleteById(Long id) throws NotFoundException, SQLException;
}
