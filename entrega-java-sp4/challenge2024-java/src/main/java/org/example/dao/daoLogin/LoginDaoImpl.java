package org.example.dao.daoLogin;

import org.example.config.DatabaseConnectionFactory;
import org.example.model.informacoesPessoais.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LoginDaoImpl implements LoginDao{

    private Connection connection;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public LoginDaoImpl() {
    }


    @Override
    public List<Pessoa> checkLogin(String login, String senha) {
        List<Pessoa> resultado = new ArrayList<>();
        final String sql = "SELECT * FROM t_atc_usuario WHERE (NM_EMAIL = ?  AND NM_SENHA = ?) OR (NR_CPF = ? AND NM_SENHA = ?)";
        try (Connection connection = DatabaseConnectionFactory.create().get()) {
            PreparedStatement stmt = connection.prepareStatement(sql);

            // Definindo os parâmetros
            stmt.setString(1, login);
            stmt.setString(2, senha);
            stmt.setString(3, login);
            stmt.setString(4, senha);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("ID_USUARIO");
                String nome = rs.getString("NM_USUARIO");
                LocalDate dataNasc = rs.getDate("DT_NASC").toLocalDate();
                String cpf = rs.getString("NR_CPF");
                String email = rs.getString("NM_EMAIL");
                String senhaDb = rs.getString("NM_SENHA");
                resultado.add(new Pessoa(id, nome, dataNasc, cpf, email, senhaDb));
            }
        } catch(SQLException e) {
            logger.warning("Não foi possível localizar nenhum registro de pessoa: " + e.getMessage());
        }
        return resultado;
    }



}
