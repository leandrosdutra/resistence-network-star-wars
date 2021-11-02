package com.resistence.network.application.transformer;

import com.resistence.network.application.dto.RebeldeDto;
import com.resistence.network.application.dto.request.RebeldeRequest;
import com.resistence.network.domain.Localizacao;
import com.resistence.network.domain.Rebelde;
import com.resistence.network.domain.RebeldeItem;
import com.resistence.network.domain.enums.GeneroEnum;
import java.util.List;
import java.util.stream.Collectors;

public class RebeldeTransformer {

    private static final int QTD_TRAICOES_INICIAL = 0;

    private RebeldeTransformer() {
    }

    public static Rebelde toEntity(RebeldeRequest rebeldeRequest){
        var rebelde = new Rebelde();
        rebelde.setNome(rebeldeRequest.getNome());
        rebelde.setIdade(rebeldeRequest.getIdade());
        rebelde.setGenero(rebeldeRequest.getGenero().getSigla());
        rebelde.setQtdTraicoes(QTD_TRAICOES_INICIAL);
        return rebelde;
    }

    public static RebeldeDto toDto(Rebelde rebelde){
        var rebeldeDto = new RebeldeDto();
        rebeldeDto.setOid(rebelde.getOidRebelde());
        rebeldeDto.setNome(rebelde.getNome());
        rebeldeDto.setIdade(rebelde.getIdade());
        rebeldeDto.setGenero(GeneroEnum.fromSigla(rebelde.getGenero()));
        rebeldeDto.setQtdTraicoes(rebelde.getQtdTraicoes());
        rebeldeDto.setTraidor(rebelde.isTraidor());
        rebeldeDto.setLocalizacao(LocalizacaoTransformer.toDto(rebelde.getLocalizacao()));
        rebeldeDto.setItens(rebelde.getRebeldeItemList().stream().map(ItemTransformer::toDto).collect(Collectors.toList()));
        return rebeldeDto;
    }

    public static RebeldeDto toDto(Rebelde rebelde, Localizacao localizacao, List<RebeldeItem> itensRebeldeList){
        var rebeldeDto = new RebeldeDto();
        rebeldeDto.setOid(rebelde.getOidRebelde());
        rebeldeDto.setNome(rebelde.getNome());
        rebeldeDto.setIdade(rebelde.getIdade());
        rebeldeDto.setGenero(GeneroEnum.fromSigla(rebelde.getGenero()));
        rebeldeDto.setQtdTraicoes(rebelde.getQtdTraicoes());
        rebeldeDto.setTraidor(rebelde.isTraidor());
        rebeldeDto.setLocalizacao(LocalizacaoTransformer.toDto(localizacao));
        rebeldeDto.setItens(itensRebeldeList.stream().map(ItemTransformer::toDto).collect(Collectors.toList()));
        return rebeldeDto;
    }

}