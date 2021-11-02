package com.resistence.network.application.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.resistence.network.application.util.ConstantsUtil;
import com.resistence.network.domain.Item;
import com.resistence.network.domain.enums.TipoRebeldeEnum;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Map;

public class NegociacaoRequest {

    @Positive(message = "Oid do primeiro rebelde deve ser maior que zero")
    private Long oidPrimeiroRebelde;
    private List<ItemRequest> itensPrimeiroRebeldeList;
    @Positive(message = "Oid do segundo rebelde deve ser maior que zero")
    private Long oidSegundoRebelde;
    private List<ItemRequest> itensSegundoRebeldeList;

    public Long getOidPrimeiroRebelde() { return oidPrimeiroRebelde; }

    public void setOidPrimeiroRebelde(Long oidPrimeiroRebelde) { this.oidPrimeiroRebelde = oidPrimeiroRebelde; }

    public List<ItemRequest> getItensPrimeiroRebeldeList() { return itensPrimeiroRebeldeList; }

    public void setItensPrimeiroRebeldeList(List<ItemRequest> itensPrimeiroRebeldeList) { this.itensPrimeiroRebeldeList = itensPrimeiroRebeldeList; }

    public Long getOidSegundoRebelde() { return oidSegundoRebelde; }

    public void setOidSegundoRebelde(Long oidSegundoRebelde) { this.oidSegundoRebelde = oidSegundoRebelde; }

    public List<ItemRequest> getItensSegundoRebeldeList() { return itensSegundoRebeldeList; }

    public void setItensSegundoRebeldeList(List<ItemRequest> itensSegundoRebeldeList) { this.itensSegundoRebeldeList = itensSegundoRebeldeList; }

    @JsonIgnore
    public List<ItemRequest> getItens(TipoRebeldeEnum tipoRebeldeEnum){
        return TipoRebeldeEnum.PRIMEIRO.equals(tipoRebeldeEnum) ? this.itensPrimeiroRebeldeList : this.itensSegundoRebeldeList;
    }

    @JsonIgnore
    public boolean verificarPontuacaoDosItens(Map<String, Item> itemMap){
        return getSomatorioPontuacaoDosItens(TipoRebeldeEnum.PRIMEIRO, itemMap) == getSomatorioPontuacaoDosItens(TipoRebeldeEnum.SEGUNDO, itemMap);
    }

    @JsonIgnore
    private int getSomatorioPontuacaoDosItens(TipoRebeldeEnum tipoRebeldeEnum, Map<String, Item> itemMap){
        return getItens(tipoRebeldeEnum).stream()
                                        .map(it -> it.getQuantidade() * itemMap.get(it.getNome()).getPontuacao())
                                        .reduce(ConstantsUtil.VALOR_REDUCE, Integer::sum);
    }

    @Override
    public String toString() {
        return "NegociacaoRequest{" +
                "oidPrimeiroRebelde=" + oidPrimeiroRebelde +
                ", itensPrimeiroRebeldeList=" + itensPrimeiroRebeldeList +
                ", oidSegundoRebelde=" + oidSegundoRebelde +
                ", itensSegundoRebeldeList=" + itensSegundoRebeldeList +
                '}';
    }
}