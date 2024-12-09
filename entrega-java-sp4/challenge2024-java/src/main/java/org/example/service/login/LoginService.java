package org.example.service.login;

import org.example.exceptions.NotFoundException;
import org.example.model.informacoesPessoais.Pessoa;

import java.util.List;

public interface LoginService {

    List<Pessoa> checkLogin(String login, String senha) throws NotFoundException;
}
