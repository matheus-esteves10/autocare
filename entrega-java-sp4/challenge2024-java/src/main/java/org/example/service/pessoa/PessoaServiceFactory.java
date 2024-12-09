package org.example.service.pessoa;

import org.example.service.Service;

public class PessoaServiceFactory {
    private PessoaServiceFactory() {
    }

    public static Service create(){
        return new PessoaServiceImpl();
    }
}
