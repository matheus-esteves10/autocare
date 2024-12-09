package org.example.service.agendamento;

import org.example.config.DatabaseConnectionFactory;
import org.example.dao.daoAgendamento.AgendamentoDaoFactory;
import org.example.dao.daoAgendamento.DaoAgendamento;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.exceptions.UnsupportedServiceOperationException;
import org.example.model.informacoesPessoais.Veiculo;
import org.example.model.oficina.Agendamentos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AgendamentoServiceImpl implements AgendamentoService {

    private final DaoAgendamento dao = AgendamentoDaoFactory.create();


    @Override
    public Agendamentos create(Agendamentos agendamento) throws UnsupportedServiceOperationException, SQLException, NotSavedException {
        if (agendamento.getId() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                agendamento = (Agendamentos) this.dao.save(agendamento, connection);
                connection.commit();
                return agendamento;
            } catch (SQLException | NotSavedException e) {
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException();
        }
    }

    @Override
    public List<Agendamentos> findAll() {
        return this.dao.readAll();
    }

    @Override
    public List<Agendamentos> findByIdPessoa(Long idPessoa) throws NotFoundException {
        return this.dao.readByIdPessoa(idPessoa);
    }

    @Override
    public List<Agendamentos> findByIdOficina(Long idOficina) throws NotFoundException {
        return this.dao.readByIdOficina(idOficina);
    }

    @Override
    public Agendamentos update(Agendamentos entity) throws NotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        entity = (Agendamentos) this.dao.update(entity, connection);
        connection.commit();
        return entity;
    }

    @Override
    public void deleteById(Long id) throws NotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id, connection);
        connection.commit();
    }
}
