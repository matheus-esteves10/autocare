package org.example.model.oficina;

public class AvaliacaoOficina extends Oficina{
    private String comentariosOficina;
    private int quantidadeDeServicos;
    private int quantidadeDeAvaliacoes;
    private int somaNotaDasAvaliacoes;
    private int notaOficina;


    public AvaliacaoOficina(Long id, String nomeOficina, String cidadeOficina, String enderecoOficina, String cnpjOficina) {
        super(id, nomeOficina, cidadeOficina, enderecoOficina, cnpjOficina);
    }

    public AvaliacaoOficina(int quantidadeDeAvaliacoes, int somaNotaDasAvaliacoes) {
        this.quantidadeDeAvaliacoes = quantidadeDeAvaliacoes;
        this.somaNotaDasAvaliacoes = somaNotaDasAvaliacoes;

    }

    public String getComentariosOficina() {
        return comentariosOficina;
    }

    public void setComentariosOficina(String comentariosOficina) {
        this.comentariosOficina = comentariosOficina;
    }

    public int getQuantidadeDeServicos() {
        return quantidadeDeServicos;
    }

    public void setQuantidadeDeServicos(int quantidadeDeServicos) {
        this.quantidadeDeServicos = quantidadeDeServicos;
    }

    public int getQuantidadeDeAvaliacoes() {
        return quantidadeDeAvaliacoes;
    }

    public void setQuantidadeDeAvaliacoes(int quantidadeDeAvaliacoes) {
        this.quantidadeDeAvaliacoes = quantidadeDeAvaliacoes;
    }

    public int getSomaNotaDasAvaliacoes() {
        return somaNotaDasAvaliacoes;
    }

    public void setSomaNotaDasAvaliacoes(int somaNotaDasAvaliacoes) {
        this.somaNotaDasAvaliacoes = somaNotaDasAvaliacoes;
    }

    public int getNotaOficina() {
        return notaOficina;
    }

    public void calculaNota(int quantidadeDeAvaliacoes, int somaNotaDasAvaliacoes, int notaOficina){
        this.notaOficina = somaNotaDasAvaliacoes / quantidadeDeAvaliacoes;
    }
}
