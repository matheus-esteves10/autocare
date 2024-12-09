package org.example.dto;

public record VeiculoDto(Long id,
                         String marca,
                         String ano,
                         String modelo,
                         String documentoVeiculo,
                         String placaVeiculo,
                         Long idPessoa
                         ) {
}
