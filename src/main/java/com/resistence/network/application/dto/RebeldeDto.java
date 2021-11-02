package com.resistence.network.application.dto;

import com.resistence.network.domain.enums.GeneroEnum;
import java.util.List;

public class RebeldeDto {

    private Long oid;
    private String nome;
    private int idade;
    private GeneroEnum genero;
    private int qtdTraicoes;
    private boolean isTraidor;
    private LocalizacaoDto localizacao;
    private List<ItemRebeldeDto> itens;

    public Long getOid() { return oid; }

    public void setOid(Long oid) { this.oid = oid; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public int getIdade() { return idade; }

    public void setIdade(int idade) { this.idade = idade; }

    public GeneroEnum getGenero() { return genero; }

    public void setGenero(GeneroEnum genero) { this.genero = genero; }

    public int getQtdTraicoes() { return qtdTraicoes; }

    public void setQtdTraicoes(int qtdTraicoes) { this.qtdTraicoes = qtdTraicoes; }

    public boolean isTraidor() { return isTraidor; }

    public void setTraidor(boolean traidor) { isTraidor = traidor; }

    public LocalizacaoDto getLocalizacao() { return localizacao; }

    public void setLocalizacao(LocalizacaoDto localizacao) { this.localizacao = localizacao; }

    public List<ItemRebeldeDto> getItens() { return itens; }

    public void setItens(List<ItemRebeldeDto> itens) { this.itens = itens; }

    @Override
    public String toString() {
        return "RebeldeDto{" +
                "oid=" + oid +
                ", nome='" + nome + '\'' +
                ", idade=" + idade +
                ", genero=" + genero +
                ", localizacao=" + localizacao +
                ", itens=" + itens +
                '}';
    }
}