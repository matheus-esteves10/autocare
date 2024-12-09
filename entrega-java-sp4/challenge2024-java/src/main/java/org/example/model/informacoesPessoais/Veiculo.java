package org.example.model.informacoesPessoais;

import java.time.LocalDate;

public class Veiculo extends Pessoa {

    private Long id;
    private String marca;
    private String ano;
    private String modelo;
    private String documentoVeiculo;
    private String placaVeiculo;
    private Long idPessoa;

    public Veiculo(Long id, String nome, LocalDate dataNacimento, String cpf, String email, String senha, String documentoVeiculo, String placaVeiculo) {
        super(id, nome, dataNacimento, cpf, email, senha);
        this.documentoVeiculo = documentoVeiculo;
        this.placaVeiculo = placaVeiculo;
    }

    public Veiculo(String documentoVeiculo, String placaVeiculo) {
        this.documentoVeiculo = documentoVeiculo;
        setPlacaVeiculo(placaVeiculo);

    }

    public Veiculo(Long id, String marca, String modelo, String ano, String documentoVeiculo, String placaVeiculo, Long idPessoa) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.documentoVeiculo = documentoVeiculo;
        this.placaVeiculo = placaVeiculo;
        this.idPessoa = idPessoa;
    }

    public Veiculo(Long id, String marca, String modelo, String ano, String documentoVeiculo, String placaVeiculo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.documentoVeiculo = documentoVeiculo;
        this.placaVeiculo = placaVeiculo;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDocumentoVeiculo() {
        return documentoVeiculo;
    }

    public void setDocumentoVeiculo(String documentoVeiculo) {
        this.documentoVeiculo = documentoVeiculo;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        if(isPlacaValida(placaVeiculo)) {
            this.placaVeiculo = placaVeiculo;
        } else {
            throw new RuntimeException("Placa inválida");
        }
    }

    public boolean isPlacaValida(String placaVeiculo) {
        if (placaVeiculo.matches("[A-Z]{3}-\\d{4}")) {
            return true;
        }
        return false;
    }
}
