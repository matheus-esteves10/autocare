package org.example.informacoesPessoais;

import org.example.model.informacoesPessoais.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class PessoaNaoCadastradaTest {

    LocalDate dataNascimento = LocalDate.of(2000, 8, 1);
    Pessoa pessoa = new Pessoa(1L, "Carlos", dataNascimento, "044.464.790-24", "carlos@gmail.com", "1234");

    @Test
    void teste_se_o_cpf_valido_retorna_true() {
        Assertions.assertTrue(pessoa.isCpfValid("044.464.790-24"));
    }

    @Test
    void teste_se_o_cpf_invalido_retorna_false(){
        Assertions.assertFalse(pessoa.isCpfValid("12543253242"));
    }


    @Test
    void verifica_se_quando_senha_igual_retorna_true() {
        Assertions.assertTrue(pessoa.isSenhaValid("1234", "1234"));
    }

    @Test
    void verifica_se_quando_senha_diferente_retorna_false() {
        Assertions.assertFalse(pessoa.isSenhaValid("1234", "4321"));
    }
}