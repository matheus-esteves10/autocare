package org.example.dao.daoLogin;

public class LoginDaoFactory {

    private LoginDaoFactory() {
    }

    public static LoginDao create(){
        return new LoginDaoImpl();
    }
}
