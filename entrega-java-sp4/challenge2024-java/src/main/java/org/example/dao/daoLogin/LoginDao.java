package org.example.dao.daoLogin;

import org.example.exceptions.NotFoundException;
import org.example.model.informacoesPessoais.Pessoa;

import java.util.List;

public interface LoginDao {

    List<Pessoa> checkLogin(String login, String senha) throws NotFoundException;
}
