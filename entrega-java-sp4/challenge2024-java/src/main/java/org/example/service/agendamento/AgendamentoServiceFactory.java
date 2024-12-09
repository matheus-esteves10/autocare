package org.example.service.agendamento;



public class AgendamentoServiceFactory {

    private AgendamentoServiceFactory() {

    }

    public static AgendamentoService create() {
        return new AgendamentoServiceImpl();
    }


}
