package org.example.model.oficina;

import java.util.List;

public class Oficina {
    private Long id;
    private String nomeOficina;
    private String cidadeOficina;
    private String enderecoOficina;
    private String cnpjOficina;
    private List<Agendamentos> agendamentos;
    private List<AvaliacaoOficina> avaliacoes;

    public Oficina(Long id, String nomeOficina, String cidadeOficina, String enderecoOficina, String cnpjOficina) {
        this.id = id;
        this.nomeOficina = nomeOficina;
        this.cidadeOficina = cidadeOficina;
        this.enderecoOficina = enderecoOficina;
        this.cnpjOficina = cnpjOficina;
    }

    public Oficina(String nomeOficina, String cidadeOficina, String enderecoOficina, String cnpjOficina) {
        this.nomeOficina = nomeOficina;
        this.cidadeOficina = cidadeOficina;
        this.enderecoOficina = enderecoOficina;
        this.cnpjOficina = cnpjOficina;
    }

    public Oficina() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeOficina() {
        return nomeOficina;
    }

    public void setNomeOficina(String nomeOficina) {
        this.nomeOficina = nomeOficina;
    }

    public String getCidadeOficina() {
        return cidadeOficina;
    }

    public void setCidadeOficina(String cidadeOficina) {
        this.cidadeOficina = cidadeOficina;
    }

    public String getEnderecoOficina() {
        return enderecoOficina;
    }

    public void setEnderecoOficina(String enderecoOficina) {
        this.enderecoOficina = enderecoOficina;
    }

    public String getCnpjOficina() {
        return cnpjOficina;
    }

    public void setCnpjOficina(String cnpjOficina) {
        this.cnpjOficina = cnpjOficina;
    }

    public void verificarCnpj(String cnpjOficina){
        //
    }

    @Override
    public String toString() {
        return "Oficina{" +
                "id=" + id +
                ", nomeOficina='" + nomeOficina + '\'' +
                ", cidadeOficina='" + cidadeOficina + '\'' +
                ", enderecoOficina='" + enderecoOficina + '\'' +
                ", cnpjOficina='" + cnpjOficina + '\'' +
                '}';
    }
}
