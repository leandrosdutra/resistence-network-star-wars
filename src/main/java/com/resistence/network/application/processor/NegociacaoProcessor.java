package com.resistence.network.application.processor;

import com.resistence.network.application.dto.request.NegociacaoRequest;
import com.resistence.network.domain.Rebelde;

public interface NegociacaoProcessor {
    void processar(Rebelde primeiroRebelde, Rebelde segundoRebelde, NegociacaoRequest negociacaoRequest);
}