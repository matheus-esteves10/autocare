package org.example.service.oficina;

import org.example.config.DatabaseConnectionFactory;
import org.example.dao.Dao;
import org.example.dao.daoOficina.OficinaDaoFactory;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.model.oficina.Oficina;
import org.example.service.Service;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OficinaServiceImpl implements Service<Oficina> {

    private final Dao dao = OficinaDaoFactory.create();

    @Override
    public Oficina create(Oficina oficina) throws UnsupportedServiceOperationException, SQLException, NotSavedException {
        if (oficina.getId() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                oficina = (Oficina) this.dao.save(oficina, connection);
                connection.commit();
                return oficina;
            } catch (SQLException | NotSavedException e) {
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException();
        }
    }

    @Override
    public List<Oficina> findAll() {
        return this.dao.readAll();
    }

    @Override
    public Oficina findById(Long id) throws NotFoundException {
        return (Oficina) this.dao.readById(id);
    }

    @Override
    public Oficina update(Oficina oficina) throws NotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        oficina = (Oficina) this.dao.update(oficina, connection);
        connection.commit();
        return oficina;
    }

    @Override
    public void deleteById(Long id) throws NotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id, connection);
        connection.commit();
    }
}
