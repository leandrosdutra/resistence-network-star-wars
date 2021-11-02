package com.resistence.network.application.processor;

import com.resistence.network.application.dto.request.NegociacaoRequest;
import com.resistence.network.domain.Rebelde;
import com.resistence.network.domain.RebeldeItem;
import com.resistence.network.domain.exceptions.ErrorMessage;
import com.resistence.network.domain.exceptions.NotFoundException;
import com.resistence.network.infrastructure.repository.RebeldeItemRepository;
import com.resistence.network.infrastructure.repository.RebeldeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RetiradaProcessor implements NegociacaoProcessor {

    private static final Logger logger = LoggerFactory.getLogger(RetiradaProcessor.class);

    private final RebeldeItemRepository rebeldeItemRepository;
    private final RebeldeRepository rebeldeRepository;

    public RetiradaProcessor(RebeldeItemRepository rebeldeItemRepository, RebeldeRepository rebeldeRepository) {
        this.rebeldeItemRepository = rebeldeItemRepository;
        this.rebeldeRepository = rebeldeRepository;
    }

    @Override
    public void processar(Rebelde primeiroRebelde, Rebelde segundoRebelde, NegociacaoRequest negociacaoRequest) {
        logger.debug("Efetuando a retirada dos itens da negociacao");
        negociacaoRequest.getItensPrimeiroRebeldeList()
                         .forEach(it -> atualizar(primeiroRebelde.getRebeldeItemList()
                                                                 .stream()
                                                                 .filter(el -> el.getOidItem().getNome().equalsIgnoreCase(it.getNome()))
                                                                 .findAny()
                                                                 .orElseThrow(() -> new NotFoundException(ErrorMessage.MSG_012.getDescription())), it.getQuantidade()));
        negociacaoRequest.getItensSegundoRebeldeList()
                         .forEach(it -> atualizar(segundoRebelde.getRebeldeItemList()
                                                                .stream()
                                                                .filter(el -> el.getOidItem().getNome().equalsIgnoreCase(it.getNome()))
                                                                .findAny()
                                                                .orElseThrow(() -> new NotFoundException(ErrorMessage.MSG_012.getDescription())), it.getQuantidade()));
    }

    private void atualizar(RebeldeItem rebeldeItem, int quantidade){
        if(rebeldeItem.manterItem(quantidade)){
            rebeldeItemRepository.save(rebeldeItem.diminuirQuantidade(quantidade));
        } else {
            rebeldeItemRepository.delete(rebeldeItem);
        }
    }

}
