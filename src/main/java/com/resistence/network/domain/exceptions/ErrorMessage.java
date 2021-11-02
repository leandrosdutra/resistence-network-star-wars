package com.resistence.network.domain.exceptions;

public enum ErrorMessage {

    MSG_001("Sigla não encontrada."),
    MSG_002("Rebelde não encontrado"),
    MSG_003("Primeiro Rebelde não encontrado"),
    MSG_004("Segundo Rebelde não encontrado"),
    MSG_005("Primeiro Rebelde é um traidor, portanto, não pode negociar itens"),
    MSG_006("Segundo Rebelde é um traidor, portanto, não pode negociar itens"),
    MSG_007("Primeiro Rebelde não possui um dos itens a serem trocados"),
    MSG_008("Segundo Rebelde não possui um dos itens a serem trocados"),
    MSG_009("Primeiro Rebelde não possui quantidade suficiente de um dos itens a serem trocados"),
    MSG_010("Segundo Rebelde não possui quantidade suficiente de um dos itens a serem trocados"),
    MSG_011("Somatório da pontuação dos itens dos rebeldes é divergente"),
    MSG_012("Item não encontrado no inventário"),
    MSG_013("Item não cadastrado na base de dados");

    private final String description;

    ErrorMessage(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}