package com.resistence.network.application.builder;

import com.resistence.network.domain.Rebelde;

public class RebeldeBuilder {

    private Rebelde rebelde;

    private RebeldeBuilder() {
    }

    public static RebeldeBuilder umRebelde() {
        RebeldeBuilder builder = new RebeldeBuilder();
        init(builder);
        return builder;
    }

    private static void init(RebeldeBuilder builder) {
        builder.rebelde = new Rebelde();
        Rebelde rebelde = builder.rebelde;
        rebelde.setOidRebelde(1L);
        rebelde.setNome("Chewbacca");
        rebelde.setIdade(30);
        rebelde.setQtdTraicoes(0);
        rebelde.setGenero("M");
        rebelde.setLocalizacao(LocalizacaoBuilder.umaLocalizacao().now());
    }

    public Rebelde now() {
        return rebelde;
    }

    public RebeldeBuilder setTraidor() {
        rebelde.setQtdTraicoes(10);
        return this;
    }

}