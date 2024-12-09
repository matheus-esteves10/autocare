package org.example.dao.daoPessoa;

import org.example.dao.Dao;
import org.example.model.informacoesPessoais.Pessoa;

public final class PessoaDaoFactory {
    private PessoaDaoFactory() {
    }

    public static Dao<Pessoa> create(){
        return new PessoaDaoImp();
    }
}
