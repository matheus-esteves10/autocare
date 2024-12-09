package org.example.service;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.model.informacoesPessoais.Pessoa;

import java.sql.SQLException;
import java.util.List;

public interface Service <T>{
    T create(T entity) throws UnsupportedServiceOperationException, SQLException, NotSavedException;

    List<T> findAll();

    T findById(Long id) throws NotFoundException;

    T update(T entity) throws NotFoundException, SQLException;

    void deleteById(Long id) throws NotFoundException, SQLException;
}
