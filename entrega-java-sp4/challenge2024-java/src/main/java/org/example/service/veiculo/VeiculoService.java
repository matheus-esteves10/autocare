package org.example.service.veiculo;

import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.model.informacoesPessoais.Veiculo;

import java.sql.SQLException;
import java.util.List;

public interface VeiculoService {

    Veiculo create(Veiculo entity) throws UnsupportedServiceOperationException, SQLException, NotSavedException;

    List<Veiculo> findAll();

    List<Veiculo> findById(Long id) throws NotFoundException;

    Veiculo update(Veiculo entity) throws NotFoundException, SQLException;

    void deleteById(Long id) throws NotFoundException, SQLException;
}
