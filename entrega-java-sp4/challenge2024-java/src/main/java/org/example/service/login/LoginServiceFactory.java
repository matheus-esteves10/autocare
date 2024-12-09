package org.example.service.login;

public class LoginServiceFactory {

    private LoginServiceFactory() {
    }

    public static LoginService create() {
        return new LoginServiceImpl();
    }
}
