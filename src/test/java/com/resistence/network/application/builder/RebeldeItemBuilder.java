package com.resistence.network.application.builder;

import com.resistence.network.domain.RebeldeItem;

public class RebeldeItemBuilder {

    private RebeldeItem rebeldeItem;

    private RebeldeItemBuilder() {
    }

    public static RebeldeItemBuilder umRebeldeItem() {
        RebeldeItemBuilder builder = new RebeldeItemBuilder();
        init(builder);
        return builder;
    }

    private static void init(RebeldeItemBuilder builder) {
        builder.rebeldeItem = new RebeldeItem();
        RebeldeItem rebeldeItem = builder.rebeldeItem;
        rebeldeItem.setOidRebeldeItem(1L);
        rebeldeItem.setOidRebelde(RebeldeBuilder.umRebelde().now());
        rebeldeItem.setOidItem(ItemBuilder.umItem().now());
        rebeldeItem.setQuantidade(5);
    }

    public RebeldeItem now() {
        return rebeldeItem;
    }

}