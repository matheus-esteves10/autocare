package org.example.dao;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    // Create
    T save(T entity, Connection connection) throws SQLException, NotSavedException;

    // Read
    List<T> readAll();

    T readById(Long id) throws NotFoundException;

    // Update
    T update(T entity, Connection connection) throws NotFoundException, SQLException;

    // Delete
    void deleteById(Long id, Connection connection) throws SQLException, NotFoundException;
}
