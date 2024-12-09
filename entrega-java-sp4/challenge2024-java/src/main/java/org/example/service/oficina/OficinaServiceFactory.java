package org.example.service.oficina;

import org.example.service.Service;

public class OficinaServiceFactory {

    private OficinaServiceFactory() {
    }

    public static Service create(){
        return new OficinaServiceImpl();
    }
}
