package com.resistence.network.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.resistence.network.application.util.ConstantsUtil;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rebelde", schema = "db")
public class Rebelde {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oid_rebelde")
    private Long oidRebelde;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "idade", nullable = false)
    private int idade;

    @Column(name = "genero", nullable = false, length = 1)
    private String genero;

    @Column(name = "qtd_traicoes", nullable = false)
    private int qtdTraicoes;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "oidRebelde", fetch = FetchType.LAZY)
    private Localizacao localizacao;

    @OneToMany(orphanRemoval = true,  mappedBy = "oidRebelde")
    private List<RebeldeItem> rebeldeItemList;

    public Rebelde() {
        this.rebeldeItemList = new ArrayList<>();
    }

    public Long getOidRebelde() { return oidRebelde; }

    public void setOidRebelde(Long oidRebelde) { this.oidRebelde = oidRebelde; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public int getIdade() { return idade; }

    public void setIdade(int idade) { this.idade = idade; }

    public String getGenero() { return genero; }

    public void setGenero(String genero) { this.genero = genero; }

    public int getQtdTraicoes() { return qtdTraicoes; }

    public void setQtdTraicoes(int qtdTraicoes) { this.qtdTraicoes = qtdTraicoes; }

    public Localizacao getLocalizacao() { return localizacao; }

    public void setLocalizacao(Localizacao localizacao) { this.localizacao = localizacao; }

    public List<RebeldeItem> getRebeldeItemList() { return rebeldeItemList; }

    public void setRebeldeItemList(List<RebeldeItem> rebeldeItemList) { this.rebeldeItemList = rebeldeItemList; }

    @JsonIgnore
    public int incrementarQtdTraicoes(int qtdSomada){ return qtdTraicoes + qtdSomada; }

    @JsonIgnore
    public boolean isTraidor(){ return qtdTraicoes >= ConstantsUtil.QTD_LIMITE_TRAICOES; }

    @JsonIgnore
    public boolean possuiItem(String nomeItem){
        return rebeldeItemList.stream()
                              .anyMatch(it -> it.getOidItem().getNome().equalsIgnoreCase(nomeItem));
    }

    @JsonIgnore
    public boolean possuiQuantidade(String nomeItem, int quantidade){
        return rebeldeItemList.stream()
                              .filter(it -> it.getOidItem().getNome().equalsIgnoreCase(nomeItem))
                              .findFirst()
                              .stream()
                              .anyMatch(it -> it.getQuantidade() >= quantidade);

    }

}