package com.resistence.network.application.builder;

import com.resistence.network.application.dto.request.AtualizarLocalizacaoRequest;

public class AtualizarLocalizacaoRequestBuilder {

    private AtualizarLocalizacaoRequest atualizarLocalizacaoRequest;

    private AtualizarLocalizacaoRequestBuilder() {
    }

    public static AtualizarLocalizacaoRequestBuilder umaAtualizarLocalizacaoRequest() {
        AtualizarLocalizacaoRequestBuilder builder = new AtualizarLocalizacaoRequestBuilder();
        init(builder);
        return builder;
    }

    private static void init(AtualizarLocalizacaoRequestBuilder builder) {
        builder.atualizarLocalizacaoRequest = new AtualizarLocalizacaoRequest();
        AtualizarLocalizacaoRequest request = builder.atualizarLocalizacaoRequest;
        request.setOidRebelde(1L);
        request.setLocalizacao(LocalizacaoRequestBuilder.umaLocalizacaoRequest().now());
    }

    public AtualizarLocalizacaoRequest now() {
        return atualizarLocalizacaoRequest;
    }

}