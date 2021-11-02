package com.resistence.network.application.builder;

import com.resistence.network.domain.Localizacao;

public class LocalizacaoBuilder {

    private Localizacao localizacao;

    private LocalizacaoBuilder() {
    }

    public static LocalizacaoBuilder umaLocalizacao() {
        LocalizacaoBuilder builder = new LocalizacaoBuilder();
        init(builder);
        return builder;
    }

    private static void init(LocalizacaoBuilder builder) {
        builder.localizacao = new Localizacao();
        Localizacao localizacao = builder.localizacao;
        localizacao.setOidLocalizacao(1L);
        localizacao.setNome("Via Lactea");
        localizacao.setLatitude(3000);
        localizacao.setLongitude(6000);
    }

    public Localizacao now() {
        return localizacao;
    }

}