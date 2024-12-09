package org.example.service.login;

import org.example.dao.daoLogin.LoginDao;
import org.example.dao.daoLogin.LoginDaoFactory;
import org.example.exceptions.NotFoundException;
import org.example.model.informacoesPessoais.Pessoa;

import java.util.List;

public class LoginServiceImpl implements LoginService{

    private final LoginDao dao = LoginDaoFactory.create();


    @Override
    public List<Pessoa> checkLogin(String login,String senha) throws NotFoundException {
        return this.dao.checkLogin(login,senha);
    }
}
