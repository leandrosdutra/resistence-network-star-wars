package com.resistence.network.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "rebelde_item", schema = "db")
public class RebeldeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oid_rebelde_item")
    private Long oidRebeldeItem;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @JoinColumn(name = "oid_item", referencedColumnName = "oid_item")
    @ManyToOne(optional = false)
    private Item oidItem;

    @JoinColumn(name = "oid_rebelde", referencedColumnName = "oid_rebelde")
    @ManyToOne(optional = false)
    private Rebelde oidRebelde;

    public RebeldeItem() {
    }

    public Long getOidRebeldeItem() { return oidRebeldeItem; }

    public void setOidRebeldeItem(Long oidRebeldeItem) { this.oidRebeldeItem = oidRebeldeItem; }

    public Item getOidItem() { return oidItem; }

    public void setOidItem(Item oidItem) { this.oidItem = oidItem; }

    public Rebelde getOidRebelde() { return oidRebelde; }

    public void setOidRebelde(Rebelde oidRebelde) { this.oidRebelde = oidRebelde; }

    public int getQuantidade() { return quantidade; }

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    @JsonIgnore
    public boolean manterItem(int qtdTroca){ return qtdTroca < quantidade; }

    @JsonIgnore
    public RebeldeItem diminuirQuantidade(int qtdTroca){
        this.quantidade = this.quantidade - qtdTroca;
        return this;
    }

    @JsonIgnore
    public RebeldeItem somarQuantidade(int qtdTroca){
        this.quantidade = this.quantidade + qtdTroca;
        return this;
    }

}