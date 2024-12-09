package org.example.dao.daoAgendamento;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;

import org.example.model.oficina.Agendamentos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DaoAgendamento {
    // Create
    Agendamentos save(Agendamentos entity, Connection connection) throws SQLException, NotSavedException;

    // Read
    List<Agendamentos> readAll();

    List<Agendamentos> readByIdPessoa(Long idUsuario) throws NotFoundException;

    List<Agendamentos> readByIdOficina(Long idOficina) throws NotFoundException;

    // Update
    Agendamentos update(Agendamentos entity, Connection connection) throws NotFoundException, SQLException;

    // Delete
    void deleteById(Long id, Connection connection) throws SQLException, NotFoundException;
}
