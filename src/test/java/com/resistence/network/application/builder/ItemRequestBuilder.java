package com.resistence.network.application.builder;

import com.resistence.network.application.dto.request.ItemRequest;

public class ItemRequestBuilder {

    private ItemRequest itemRequest;

    private ItemRequestBuilder() {
    }

    public static ItemRequestBuilder umItemRequest() {
        ItemRequestBuilder builder = new ItemRequestBuilder();
        init(builder);
        return builder;
    }

    private static void init(ItemRequestBuilder builder) {
        builder.itemRequest = new ItemRequest();
        ItemRequest request = builder.itemRequest;
        request.setNome("Arma");
        request.setQuantidade(4);
    }

    public ItemRequest now() {
        return itemRequest;
    }

    public ItemRequestBuilder comNome(String nome) {
        itemRequest.setNome(nome);
        return this;
    }

    public ItemRequestBuilder comQuantidade(int quantidade) {
        itemRequest.setQuantidade(quantidade);
        return this;
    }

}