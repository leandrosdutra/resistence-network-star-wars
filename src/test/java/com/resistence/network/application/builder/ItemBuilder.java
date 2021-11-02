package com.resistence.network.application.builder;

import com.resistence.network.domain.Item;

public class ItemBuilder {

    private Item item;

    private ItemBuilder() {
    }

    public static ItemBuilder umItem() {
        ItemBuilder builder = new ItemBuilder();
        init(builder);
        return builder;
    }

    private static void init(ItemBuilder builder) {
        builder.item = new Item();
        Item entity = builder.item;
        entity.setOidItem(1L);
        entity.setNome("Arma");
        entity.setPontuacao(4);
    }

    public Item now() {
        return item;
    }

    public ItemBuilder comNome(String nome) {
        item.setNome(nome);
        return this;
    }

    public ItemBuilder comPontuacao(int pontuacao) {
        item.setPontuacao(pontuacao);
        return this;
    }

}