package com.resistence.network.application.builder;

import com.resistence.network.application.dto.request.RebeldeRequest;
import com.resistence.network.domain.enums.GeneroEnum;
import java.util.List;

public class RebeldeRequestBuilder {

    private RebeldeRequest rebeldeRequest;

    private RebeldeRequestBuilder() {
    }

    public static RebeldeRequestBuilder umRebeldeRequest() {
        RebeldeRequestBuilder builder = new RebeldeRequestBuilder();
        init(builder);
        return builder;
    }

    private static void init(RebeldeRequestBuilder builder) {
        builder.rebeldeRequest = new RebeldeRequest();
        RebeldeRequest rebeldeRequest = builder.rebeldeRequest;
        rebeldeRequest.setNome("Chewbacca");
        rebeldeRequest.setGenero(GeneroEnum.MASCULINO);
        rebeldeRequest.setIdade(30);
        rebeldeRequest.setLocalizacao(LocalizacaoRequestBuilder.umaLocalizacaoRequest().now());
        rebeldeRequest.setInventario(List.of(ItemRequestBuilder.umItemRequest().now()));
    }

    public RebeldeRequest now() {
        return rebeldeRequest;
    }

}