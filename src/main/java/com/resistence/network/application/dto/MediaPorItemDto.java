package com.resistence.network.application.dto;

import com.resistence.network.domain.RebeldeItem;

public class MediaPorItemDto {

    private String nomeItem;
    private int mediaPorRebelde;

    public MediaPorItemDto() {
    }

    public MediaPorItemDto(RebeldeItem rebeldeItem) {
        this.nomeItem = rebeldeItem.getOidItem().getNome();
        this.mediaPorRebelde = rebeldeItem.getQuantidade();
    }

    public String getNomeItem() { return nomeItem; }

    public int getMediaPorRebelde() { return mediaPorRebelde; }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public void setMediaPorRebelde(int mediaPorRebelde) {
        this.mediaPorRebelde = mediaPorRebelde;
    }
}