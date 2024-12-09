package org.example.informacoesPessoais;

import org.example.model.informacoesPessoais.Veiculo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VeiculoTest {

    Veiculo veiculo = new Veiculo("111111111", "EVK-7435");

    @Test
    void verificar_se_quando_placa_valida_retorna_true() {;
        Assertions.assertTrue(veiculo.isPlacaValida("EVK-7435"));
    }

    @Test
    void verificar_se_quando_placa_invalida_retorna_false(){
        Assertions.assertFalse(veiculo.isPlacaValida("12345"));
    }
}