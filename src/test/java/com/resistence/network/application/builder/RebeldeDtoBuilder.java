package com.resistence.network.application.builder;

import com.resistence.network.application.dto.RebeldeDto;

public class RebeldeDtoBuilder {

    private RebeldeDto rebeldeDto;

    private RebeldeDtoBuilder() {
    }

    public static RebeldeDtoBuilder umRebeldeDto() {
        RebeldeDtoBuilder builder = new RebeldeDtoBuilder();
        init(builder);
        return builder;
    }

    private static void init(RebeldeDtoBuilder builder) {
        builder.rebeldeDto = new RebeldeDto();
        RebeldeDto dto = builder.rebeldeDto;
        dto.setOid(1L);
        dto.setNome("Chewbacca");
        dto.setIdade(30);
        dto.setQtdTraicoes(0);
    }

    public RebeldeDto now() {
        return rebeldeDto;
    }

}