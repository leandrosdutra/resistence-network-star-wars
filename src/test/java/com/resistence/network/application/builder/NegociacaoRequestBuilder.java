package com.resistence.network.application.builder;

import com.resistence.network.application.dto.request.NegociacaoRequest;
import java.util.List;

public class NegociacaoRequestBuilder {

    private NegociacaoRequest negociacaoRequest;

    private NegociacaoRequestBuilder() {
    }

    public static NegociacaoRequestBuilder umaNegociacaoRequest() {
        NegociacaoRequestBuilder builder = new NegociacaoRequestBuilder();
        init(builder);
        return builder;
    }

    private static void init(NegociacaoRequestBuilder builder) {
        builder.negociacaoRequest = new NegociacaoRequest();
        NegociacaoRequest request = builder.negociacaoRequest;
        request.setOidPrimeiroRebelde(1L);
        request.setOidSegundoRebelde(2L);
        request.setItensPrimeiroRebeldeList(List.of(ItemRequestBuilder.umItemRequest().now()));
        request.setItensSegundoRebeldeList(List.of(ItemRequestBuilder.umItemRequest().now()));
    }

    public NegociacaoRequest now() {
        return negociacaoRequest;
    }

}