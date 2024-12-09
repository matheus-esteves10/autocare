package org.example.dao.daoAgendamento;

import org.example.config.DatabaseConnectionFactory;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.model.oficina.Agendamentos;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AgendamentoDaoImpl implements DaoAgendamento{

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public AgendamentoDaoImpl() {
    }

    public AgendamentoDaoImpl(Connection connection) {
    }


    @Override
    public Agendamentos save(Agendamentos agendamento, Connection connection) throws SQLException, NotSavedException {
        String sql = "BEGIN INSERT INTO T_ATC_AGENDAMENTO (dt_agendamento, hora_agendamento, ds_servico, T_ATC_USUARIO_ID_USUARIO, T_ATC_OFICINA_ID_OFICINA) VALUES (?, ?, ?, ?, ?) RETURNING ID_AGENDAMENTO INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);

        call.setDate(1, java.sql.Date.valueOf(agendamento.getDataAgendamento()));
        call.setTime(2, java.sql.Time.valueOf(agendamento.getHoraDoAgendamento()));
        call.setString(3, agendamento.getDescricaoServico());
        call.setLong(4, agendamento.getIdPessoa());
        call.setLong(5, agendamento.getIdOficina());

        call.registerOutParameter(6, java.sql.Types.NUMERIC);

        call.execute();

        long id = call.getLong(6);

        if (id == 0) {
            throw new NotSavedException();
        }

        agendamento.setId(id);

        return agendamento;
    }



    @Override
    public List<Agendamentos> readAll() {
        List<Agendamentos> resultado = new ArrayList<>();
        final String sql = "SELECT * FROM t_atc_agendamento";
        try (Connection connection = DatabaseConnectionFactory.create().get();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Long id = rs.getLong("ID_AGENDAMENTO");
                LocalDate dataAgendamento = rs.getDate("DT_AGENDAMENTO").toLocalDate();
                LocalTime horaDoAgendamento = rs.getTime("HORA_AGENDAMENTO").toLocalTime();
                String descricaoServico = rs.getString("DS_SERVICO");
                Long idUsuario = rs.getLong("T_ATC_USUARIO_ID_USUARIO");
                Long idOficina = rs.getLong("T_ATC_OFICINA_ID_OFICINA");
                resultado.add(new Agendamentos(id, dataAgendamento, horaDoAgendamento, descricaoServico, idUsuario, idOficina));
            }
        } catch (SQLException e) {
            logger.warning("Não foi possível localizar nenhum registro de agendamento: " + e.getMessage());
        }
        return resultado;
    }


    @Override
    public List<Agendamentos> readByIdPessoa(Long idUsuario) throws NotFoundException {
        final String sql = "SELECT * FROM t_atc_agendamento WHERE T_ATC_USUARIO_ID_USUARIO = ?";
        List<Agendamentos> agendamentos = new ArrayList<>();

        try (Connection connection = DatabaseConnectionFactory.create().get();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("ID_AGENDAMENTO");
                LocalDate dataAgendamento = rs.getDate("DT_AGENDAMENTO").toLocalDate();
                LocalTime horaDoAgendamento = rs.getTime("HORA_AGENDAMENTO").toLocalTime();
                String descricaoServico = rs.getString("DS_SERVICO");
                Long idOficina = rs.getLong("T_ATC_OFICINA_ID_OFICINA");

                agendamentos.add(new Agendamentos(id, dataAgendamento, horaDoAgendamento, descricaoServico, idUsuario, idOficina));
            }
            if (agendamentos.isEmpty()) {
                throw new NotFoundException();
            }

            return agendamentos;

        } catch (SQLException e) {
            logger.warning("Erro ao buscar agendamentos pelo ID do usuário: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar agendamentos pelo ID do usuário", e);
        }
    }

    @Override
    public List<Agendamentos> readByIdOficina(Long idOficina) throws NotFoundException {
        final String sql = "SELECT * FROM t_atc_agendamento WHERE T_ATC_OFICINA_ID_OFICINA = ?";
        List<Agendamentos> agendamentos = new ArrayList<>();

        try (Connection connection = DatabaseConnectionFactory.create().get();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idOficina);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("ID_AGENDAMENTO");
                LocalDate dataAgendamento = rs.getDate("DT_AGENDAMENTO").toLocalDate();
                LocalTime horaDoAgendamento = rs.getTime("HORA_AGENDAMENTO").toLocalTime();
                String descricaoServico = rs.getString("DS_SERVICO");
                Long idUsuario = rs.getLong("T_ATC_USUARIO_ID_USUARIO");

                agendamentos.add(new Agendamentos(id, dataAgendamento, horaDoAgendamento, descricaoServico, idUsuario, idOficina));
            }
            if (agendamentos.isEmpty()) {
                throw new NotFoundException();
            }

            return agendamentos;

        } catch (SQLException e) {
            logger.warning("Erro ao buscar agendamentos pelo ID do usuário: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar agendamentos pelo ID do usuário", e);
        }
    }


    @Override
    public Agendamentos update(Agendamentos agendamento, Connection connection) throws NotFoundException, SQLException {
        final String sql = "UPDATE t_atc_agendamento SET dt_agendamento = ?, hora_agendamento = ?, ds_servico = ? WHERE id_agendamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(agendamento.getDataAgendamento()));
            stmt.setTime(2, java.sql.Time.valueOf(agendamento.getHoraDoAgendamento()));
            stmt.setString(3, agendamento.getDescricaoServico());
            stmt.setLong(4, agendamento.getId());

            int linhasAlteradas = stmt.executeUpdate();
            if (linhasAlteradas == 0) {
                throw new NotFoundException();
            }
            return agendamento;
        }
    }


    @Override
    public void deleteById(Long id, Connection connection) throws SQLException, NotFoundException {
        final String sql = "DELETE FROM t_atc_agendamento WHERE id_agendamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int linhasAlteradas = stmt.executeUpdate();
            if (linhasAlteradas == 0) {
                throw new NotFoundException();
            }
        }
    }

}
