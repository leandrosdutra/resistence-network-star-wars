package com.resistence.network.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "item", schema = "db")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oid_item")
    private Long oidItem;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "pontuacao", nullable = false)
    private int pontuacao;

    @OneToMany(orphanRemoval = true, mappedBy = "oidItem")
    private List<RebeldeItem> rebeldeItemList;

    public Long getOidItem() { return oidItem; }

    public void setOidItem(Long oidItem) { this.oidItem = oidItem; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public int getPontuacao() { return pontuacao; }

    public void setPontuacao(int pontuacao) { this.pontuacao = pontuacao; }

    public List<RebeldeItem> getRebeldeItens() { return rebeldeItemList; }

    public void setRebeldeItens(List<RebeldeItem> rebeldeItemList) { this.rebeldeItemList = rebeldeItemList; }

}