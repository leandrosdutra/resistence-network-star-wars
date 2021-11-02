package com.resistence.network.application.builder;

import com.resistence.network.application.dto.request.LocalizacaoRequest;

public class LocalizacaoRequestBuilder {

    private LocalizacaoRequest localizacaoRequest;

    private LocalizacaoRequestBuilder() {
    }

    public static LocalizacaoRequestBuilder umaLocalizacaoRequest() {
        LocalizacaoRequestBuilder builder = new LocalizacaoRequestBuilder();
        init(builder);
        return builder;
    }

    private static void init(LocalizacaoRequestBuilder builder) {
        builder.localizacaoRequest = new LocalizacaoRequest();
        LocalizacaoRequest request = builder.localizacaoRequest;
        request.setNome("Via Lactea");
        request.setLatitude(3000);
        request.setLongitude(6000);
    }

    public LocalizacaoRequest now() {
        return localizacaoRequest;
    }

}