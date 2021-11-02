package com.resistence.network.application.dto;

import com.resistence.network.application.util.ConstantsUtil;
import java.math.BigDecimal;
import java.text.NumberFormat;

public class DadosRelatorioDto {

    private BigDecimal percentualTraidores;
    private BigDecimal percentualRebeldes;
    private int pontosPerdidos;

    public DadosRelatorioDto(BigDecimal percentualTraidores, BigDecimal percentualRebeldes, int pontosPerdidos) {
        this.percentualTraidores = percentualTraidores;
        this.percentualRebeldes = percentualRebeldes;
        this.pontosPerdidos = pontosPerdidos;
    }

    public String getPercentualTraidores() {
        NumberFormat formatter = NumberFormat.getPercentInstance(ConstantsUtil.LOCALE_BR);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        return formatter.format(percentualTraidores);
    }

    public String getPercentualRebeldes() {
        NumberFormat formatter = NumberFormat.getPercentInstance(ConstantsUtil.LOCALE_BR);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        return formatter.format(percentualRebeldes);
    }

    public int getPontosPerdidos() { return pontosPerdidos; }

}
