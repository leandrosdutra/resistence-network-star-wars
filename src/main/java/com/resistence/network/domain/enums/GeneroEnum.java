package com.resistence.network.domain.enums;

import com.resistence.network.domain.exceptions.ErrorMessage;
import com.resistence.network.domain.exceptions.NotFoundException;
import java.util.EnumSet;

public enum GeneroEnum {

    MASCULINO("M"),
    FEMININO("F"),
    NAO_BINARIO("N");

    private String sigla;

    GeneroEnum(String sigla) { this.sigla = sigla; }

    public String getSigla() { return sigla; }

    public static GeneroEnum fromSigla(String sigla) {
        return EnumSet.allOf(GeneroEnum.class)
                      .stream()
                      .filter(g -> sigla.equalsIgnoreCase(g.sigla))
                      .findAny()
                      .orElseThrow(() -> new NotFoundException(ErrorMessage.MSG_001.getDescription()));
    }

}