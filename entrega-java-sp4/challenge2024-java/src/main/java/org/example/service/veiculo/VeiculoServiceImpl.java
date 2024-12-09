package org.example.service.veiculo;

import org.example.config.DatabaseConnectionFactory;
import org.example.dao.Dao;
import org.example.dao.daoPessoa.PessoaDaoFactory;
import org.example.dao.daoVeiculo.DaoVeiculo;
import org.example.dao.daoVeiculo.VeiculoDaoFactory;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.model.informacoesPessoais.Veiculo;
import org.example.service.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VeiculoServiceImpl implements VeiculoService {

    private final DaoVeiculo dao = VeiculoDaoFactory.create();


    @Override
    public Veiculo create(Veiculo veiculo) throws UnsupportedServiceOperationException, SQLException, NotSavedException {
        if (veiculo.getId() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                veiculo = (Veiculo) this.dao.save(veiculo, connection);
                connection.commit();
                return veiculo;
            } catch (SQLException | NotSavedException e) {
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException();
        }
    }

    @Override
    public List<Veiculo> findAll() {
        return this.dao.readAll();
    }

    @Override
    public List<Veiculo> findById(Long id) throws NotFoundException {
        return this.dao.readById(id);
    }

    @Override
    public Veiculo update(Veiculo veiculo) throws NotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        veiculo = (Veiculo) this.dao.update(veiculo, connection);
        connection.commit();
        return veiculo;
    }

    @Override
    public void deleteById(Long id) throws NotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id, connection);
        connection.commit();
    }
}
