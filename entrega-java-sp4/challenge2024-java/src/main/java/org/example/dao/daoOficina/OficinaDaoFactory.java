package org.example.dao.daoOficina;

import org.example.dao.Dao;
import org.example.dao.daoPessoa.PessoaDaoImp;
import org.example.model.informacoesPessoais.Pessoa;
import org.example.model.oficina.Oficina;

public class OficinaDaoFactory {

    private OficinaDaoFactory() {
    }

    public static Dao<Oficina> create(){
        return new OficinaDaoImpl();
    }

}
