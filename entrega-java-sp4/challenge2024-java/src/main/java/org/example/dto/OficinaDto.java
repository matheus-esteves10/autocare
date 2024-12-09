package org.example.dto;

public record OficinaDto (Long id,
                          String nomeOficina,
                          String cidadeOficina,
                          String enderecoOficina,
                          String cnpjOficina) {
}
