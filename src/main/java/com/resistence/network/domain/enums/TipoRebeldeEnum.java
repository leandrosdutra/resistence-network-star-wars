package com.resistence.network.domain.enums;

import com.resistence.network.domain.exceptions.ErrorMessage;

public enum TipoRebeldeEnum {

    PRIMEIRO(ErrorMessage.MSG_003.getDescription(), ErrorMessage.MSG_005.getDescription(), ErrorMessage.MSG_007.getDescription(), ErrorMessage.MSG_009.getDescription()),
    SEGUNDO(ErrorMessage.MSG_004.getDescription(), ErrorMessage.MSG_006.getDescription(), ErrorMessage.MSG_008.getDescription(), ErrorMessage.MSG_010.getDescription());

    private String mensagemNaoEncontrado;
    private String mensagemTraidor;
    private String mensagemNaoPossuiItem;
    private String mensagemNaoPossuiQuantidade;

    TipoRebeldeEnum(String mensagemNaoEncontrado, String mensagemTraidor, String mensagemNaoPossuiItem, String mensagemNaoPossuiQuantidade) {
        this.mensagemNaoEncontrado = mensagemNaoEncontrado;
        this.mensagemTraidor = mensagemTraidor;
        this.mensagemNaoPossuiItem = mensagemNaoPossuiItem;
        this.mensagemNaoPossuiQuantidade = mensagemNaoPossuiQuantidade;
    }

    public String getMensagemNaoEncontrado() { return mensagemNaoEncontrado; }

    public String getMensagemTraidor() { return mensagemTraidor; }

    public String getMensagemNaoPossuiItem() { return mensagemNaoPossuiItem; }

    public String getMensagemNaoPossuiQuantidade() { return mensagemNaoPossuiQuantidade; }

}