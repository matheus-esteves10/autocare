package org.example.dao.daoVeiculo;

import org.example.dao.Dao;
import org.example.dao.daoPessoa.PessoaDaoImp;
import org.example.model.informacoesPessoais.Pessoa;
import org.example.model.informacoesPessoais.Veiculo;

public class VeiculoDaoFactory {

    private VeiculoDaoFactory() {
    }

    public static DaoVeiculo create() {
        return new VeiculoDaoImp();
    }
}
