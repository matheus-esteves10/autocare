package org.example.dao.daoOficina;

import org.example.config.DatabaseConnectionFactory;
import org.example.dao.Dao;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.NotSavedException;
import org.example.model.oficina.Oficina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OficinaDaoImpl implements Dao<Oficina> {
    private final Logger logger = Logger.getLogger(this.getClass().getName());



    @Override
    public Oficina save(Oficina oficina, Connection connection) throws SQLException, NotSavedException {

        String sql = "BEGIN INSERT INTO t_atc_oficina (nm_oficina, nm_cidade, endereco, nr_cnpj) VALUES (?, ?, ?, ?) RETURNING ID_OFICINA INTO ?; END;";

        try (CallableStatement call = connection.prepareCall(sql)) {
            call.setString(1, oficina.getNomeOficina());
            call.setString(2, oficina.getCidadeOficina());
            call.setString(3, oficina.getEnderecoOficina());
            call.setString(4, oficina.getCnpjOficina());

            call.registerOutParameter(5, java.sql.Types.NUMERIC);
            call.execute();

            long id = call.getLong(5);

            if (id == 0) {
                throw new NotSavedException();
            }
            oficina.setId(id);

            return oficina;
        }
    }

    @Override
    public List<Oficina> readAll()  {
        List<Oficina> resultado = new ArrayList<>();
        final String sql = "SELECT * FROM T_ATC_OFICINA";
        try(Connection connection = DatabaseConnectionFactory.create().get()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Long id = rs.getLong("id_oficina");
                String nomeOficina = rs.getString("nm_oficina");
                String cidadeOficina = rs.getString("nm_cidade");
                String enderecoOficina = rs.getString("endereco");
                String cnpj = rs.getString("nr_cnpj");
                resultado.add(new Oficina(id, nomeOficina, cidadeOficina, enderecoOficina, cnpj));
            }
        } catch (SQLException e){
            logger.warning("não foi possível localizar nenhum registro de pessoa: "+e.getMessage());
        }
        return resultado;
    }

    @Override
    public Oficina readById(Long id) throws NotFoundException {
        final String sql = "SELECT * FROM t_atc_oficina WHERE id_oficina = ?";
        try (Connection connection = DatabaseConnectionFactory.create().get();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    String nomeOficina = rs.getString("nm_oficina");
                    String cidadeOficina = rs.getString("nm_cidade");
                    String enderecoOficina = rs.getString("endereco");
                    String cnpj = rs.getString("nr_cnpj");
                    return new Oficina(id, nomeOficina, cidadeOficina, enderecoOficina, cnpj);
                } else {
                    throw new NotFoundException();
                }
            }
        } catch (SQLException e) {
            logger.warning("Erro ao localizar oficina: " + e.getMessage());
            throw new RuntimeException("Erro de banco de dados ao buscar oficina por ID.", e);
        }
    }


    @Override
    public Oficina update(Oficina oficina, Connection connection) throws NotFoundException, SQLException {
        final String sql = "UPDATE t_atc_oficina set nr_cnpj = ?, nm_cidade = ?, endereco = ?, nm_oficina = ? WHERE id_oficina = ?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, oficina.getCnpjOficina());
        stmt.setString(2, oficina.getCidadeOficina());
        stmt.setString(3, oficina.getEnderecoOficina());
        stmt.setString(4, oficina.getNomeOficina());
        stmt.setLong(5, oficina.getId());
        int linhasAlteradas = stmt.executeUpdate();
        if(linhasAlteradas == 0) {
            throw new NotFoundException();
        }
        return oficina;
    }

    @Override
    public void deleteById(Long id, Connection connection) throws SQLException, NotFoundException {
        final String sql = "DELETE FROM t_atc_oficina WHERE id_oficina = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();
        if(linhasAlteradas == 0) {
            throw new NotFoundException();
        }
    }

}
