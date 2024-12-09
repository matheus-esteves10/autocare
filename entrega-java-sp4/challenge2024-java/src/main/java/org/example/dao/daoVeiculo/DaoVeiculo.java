package org.example.dao.daoVeiculo;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.model.informacoesPessoais.Veiculo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DaoVeiculo {
    // Create
    Veiculo save(Veiculo entity, Connection connection) throws SQLException, NotSavedException;

    // Read
    List<Veiculo> readAll();

    List<Veiculo> readById(Long id) throws NotFoundException;

    // Update
    Veiculo update(Veiculo entity, Connection connection) throws NotFoundException, SQLException;

    // Delete
    void deleteById(Long id, Connection connection) throws SQLException, NotFoundException;
}
