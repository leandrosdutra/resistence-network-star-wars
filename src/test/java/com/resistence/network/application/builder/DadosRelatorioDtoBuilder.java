package com.resistence.network.application.builder;

import com.resistence.network.application.dto.DadosRelatorioDto;
import java.math.BigDecimal;

public class DadosRelatorioDtoBuilder {

    private DadosRelatorioDto dadosRelatorioDto;

    private DadosRelatorioDtoBuilder() {
    }

    public static DadosRelatorioDtoBuilder umDadosRelatorioDto() {
        DadosRelatorioDtoBuilder builder = new DadosRelatorioDtoBuilder();
        init(builder);
        return builder;
    }

    private static void init(DadosRelatorioDtoBuilder builder) {
        builder.dadosRelatorioDto = new DadosRelatorioDto(BigDecimal.valueOf(80L), BigDecimal.valueOf(20L), 150);
    }

    public DadosRelatorioDto now() {
        return dadosRelatorioDto;
    }

}