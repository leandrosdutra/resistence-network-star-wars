package com.resistence.network.application.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.resistence.network.domain.Item;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Map;

public class ItemRequest {

    @Size(min=1, max=50, message = "Nome do item deve possuir no máximo 50 caracteres")
    @NotEmpty(message = "Nome do item obrigatório")
    private String nome;
    @Positive(message = "Quantidade deve ser maior que zero")
    @NotEmpty(message = "Quantidade obrigatória")
    private int quantidade;

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public int getQuantidade() { return quantidade; }

    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    @JsonIgnore
    public boolean isNaoExistente(Map<String, Item> itemMap){ return ! itemMap.containsKey(nome); }

    @Override
    public String toString() {
        return "ItemRequest{" +
                "nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                '}';
    }
}