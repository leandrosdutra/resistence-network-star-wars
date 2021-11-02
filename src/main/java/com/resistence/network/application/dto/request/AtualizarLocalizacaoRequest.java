package com.resistence.network.application.dto.request;

public class AtualizarLocalizacaoRequest {

    private Long oidRebelde;
    private LocalizacaoRequest localizacao;

    public Long getOidRebelde() { return oidRebelde; }

    public void setOidRebelde(Long oidRebelde) { this.oidRebelde = oidRebelde; }

    public LocalizacaoRequest getLocalizacao() { return localizacao; }

    public void setLocalizacao(LocalizacaoRequest localizacao) { this.localizacao = localizacao; }

}