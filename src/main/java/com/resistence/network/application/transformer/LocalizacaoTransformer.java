package com.resistence.network.application.transformer;

import com.resistence.network.application.dto.LocalizacaoDto;
import com.resistence.network.application.dto.request.LocalizacaoRequest;
import com.resistence.network.domain.Localizacao;
import com.resistence.network.domain.Rebelde;

public class LocalizacaoTransformer {

    private LocalizacaoTransformer() {
    }

    public static Localizacao toEntity(LocalizacaoRequest localizacaoRequest, Rebelde rebelde){
        var localizacao = new Localizacao();
        localizacao.setNome(localizacaoRequest.getNome());
        localizacao.setLatitude(localizacaoRequest.getLatitude());
        localizacao.setLongitude(localizacaoRequest.getLongitude());
        localizacao.setOidRebelde(rebelde);
        return localizacao;
    }

    public static LocalizacaoDto toDto(Localizacao localizacao){
        var localizacaoDto = new LocalizacaoDto();
        localizacaoDto.setOid(localizacao.getOidLocalizacao());
        localizacaoDto.setNome(localizacao.getNome());
        localizacaoDto.setLatitude(localizacao.getLatitude());
        localizacaoDto.setLongitude(localizacao.getLongitude());
        return localizacaoDto;
    }

}