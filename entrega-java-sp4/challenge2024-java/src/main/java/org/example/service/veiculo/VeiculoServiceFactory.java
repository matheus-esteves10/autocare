package org.example.service.veiculo;

import org.example.service.Service;

public class VeiculoServiceFactory {
    private VeiculoServiceFactory() {
    }

    public static VeiculoService create() {
        return new VeiculoServiceImpl();
    }
}
