package org.example.oficina;

import org.example.model.oficina.AvaliacaoOficina;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AvaliacaoOficinaTest {


   AvaliacaoOficina oficina1 = new AvaliacaoOficina(10,100);
    @Test
    void verifica_se_quando_soma_das_notas_100_e_quantidade_de_avaliacoes_10_notaOficina_igual_10(){
        oficina1.calculaNota(oficina1.getQuantidadeDeAvaliacoes(), oficina1.getSomaNotaDasAvaliacoes(), oficina1.getNotaOficina() );
        Assertions.assertEquals(10, oficina1.getNotaOficina());
    }

}