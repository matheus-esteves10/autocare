package org.example.service.pessoa;

import org.example.config.DatabaseConnectionFactory;
import org.example.dao.Dao;
import org.example.dao.daoPessoa.PessoaDaoFactory;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.model.informacoesPessoais.Pessoa;
import org.example.service.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

final class PessoaServiceImpl implements Service<Pessoa> {

    private final Dao dao = PessoaDaoFactory.create();

    @Override
    public Pessoa create(Pessoa pessoa) throws UnsupportedServiceOperationException, SQLException, NotSavedException {
        if (pessoa.getId() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                pessoa = (Pessoa) this.dao.save(pessoa, connection);
                connection.commit();
                return pessoa;
            } catch (SQLException | NotSavedException e) {
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException();
        }
    }

    @Override
    public List<Pessoa> findAll() {
        return this.dao.readAll();
    }

    @Override
    public Pessoa findById(Long id) throws NotFoundException {
        return (Pessoa) this.dao.readById(id);
    }


    @Override
    public Pessoa update(Pessoa pessoa) throws NotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        pessoa = (Pessoa) this.dao.update(pessoa, connection);
        connection.commit();
        return pessoa;
    }

    @Override
    public void deleteById(Long id) throws NotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id, connection);
        connection.commit();
    }
}
