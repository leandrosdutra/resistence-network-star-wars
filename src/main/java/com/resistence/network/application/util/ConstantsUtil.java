package com.resistence.network.application.util;

import java.math.BigDecimal;
import java.util.Locale;

public class ConstantsUtil {

    private ConstantsUtil() {
    }

    public static final int QTD_INCREMENTO = 1;
    public static final int QTD_LIMITE_TRAICOES = 3;
    public static final int VALOR_REDUCE = 0;
    public static final int CASAS_DECIMAIS = 4;
    public static final BigDecimal MULTIPLICADOR = BigDecimal.valueOf(100L);
    public static final Locale LOCALE_BR = new Locale("pt","BR");

}