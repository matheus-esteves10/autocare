package org.example.dto;

import org.example.model.informacoesPessoais.Veiculo;
import org.example.model.oficina.Agendamentos;

import java.time.LocalDate;
import java.util.List;

public record PessoaDto(Long id,
                        String nome,
                        LocalDate dataNacimento,
                        String cpf,
                        String email,
                        String senha) {
}
