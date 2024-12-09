package org.example.dao.daoAgendamento;

public class AgendamentoDaoFactory {

    private AgendamentoDaoFactory() {
    }

    public static DaoAgendamento create() {
        return new AgendamentoDaoImpl();
    }
}
