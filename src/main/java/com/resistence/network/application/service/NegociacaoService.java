package com.resistence.network.application.service;

import com.resistence.network.application.dto.request.NegociacaoRequest;
import com.resistence.network.application.processor.NegociacaoProcessor;
import com.resistence.network.domain.Item;
import com.resistence.network.domain.Rebelde;
import com.resistence.network.domain.enums.TipoRebeldeEnum;
import com.resistence.network.domain.exceptions.BusinessException;
import com.resistence.network.domain.exceptions.ErrorMessage;
import com.resistence.network.domain.exceptions.NotFoundException;
import com.resistence.network.infrastructure.repository.RebeldeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class NegociacaoService {

    private static final Logger logger = LoggerFactory.getLogger(NegociacaoService.class);

    private final ItemService itemService;
    private final RebeldeRepository rebeldeRepository;
    private final List<NegociacaoProcessor> processadoresDeNegociacao;

    public NegociacaoService(ItemService itemService, RebeldeRepository rebeldeRepository, List<NegociacaoProcessor> processadoresDeNegociacao) {
        this.itemService = itemService;
        this.rebeldeRepository = rebeldeRepository;
        this.processadoresDeNegociacao = processadoresDeNegociacao;
    }

    public void negociarItens(NegociacaoRequest negociacaoRequest){
        logger.debug("Iniciando a negociação de itens entre os rebeldes {}", negociacaoRequest);
        Rebelde primeiroRebelde = verificarRebeldeNegociacao(negociacaoRequest.getOidPrimeiroRebelde(), TipoRebeldeEnum.PRIMEIRO);
        Rebelde segundoRebelde = verificarRebeldeNegociacao(negociacaoRequest.getOidSegundoRebelde(), TipoRebeldeEnum.SEGUNDO);
        verificarQuantidadePontuacao(negociacaoRequest);
        verificarItensRebeldeParaNegociacao(primeiroRebelde, negociacaoRequest, TipoRebeldeEnum.PRIMEIRO);
        verificarItensRebeldeParaNegociacao(segundoRebelde, negociacaoRequest, TipoRebeldeEnum.SEGUNDO);
        atualizarItensRebelde(primeiroRebelde, segundoRebelde, negociacaoRequest);
    }

    private void verificarQuantidadePontuacao(NegociacaoRequest negociacaoRequest){
        logger.debug("Validando a pontuação dos itens dos rebeldes");
        if(!negociacaoRequest.verificarPontuacaoDosItens(itemService.listarItens().stream().collect(Collectors.toMap(Item::getNome, Function.identity())))){
            throw new BusinessException(ErrorMessage.MSG_011.getDescription());
        }
    }

    private Rebelde verificarRebeldeNegociacao(Long oidRebelde, TipoRebeldeEnum tipoRebeldeEnum){
        logger.debug("Validando o {} rebelde {}", tipoRebeldeEnum, oidRebelde);
        return Optional.of(rebeldeRepository.findById(oidRebelde)
                                            .orElseThrow(() -> new NotFoundException(tipoRebeldeEnum.getMensagemNaoEncontrado())))
                                            .filter(Predicate.not(Rebelde::isTraidor))
                                            .orElseThrow(() -> new BusinessException(tipoRebeldeEnum.getMensagemTraidor()));
    }

    private void verificarItensRebeldeParaNegociacao(Rebelde rebelde, NegociacaoRequest negociacaoRequest, TipoRebeldeEnum tipoRebeldeEnum){
        logger.debug("Validando os itens do {} rebelde envolvido na negociacao", tipoRebeldeEnum);
        negociacaoRequest.getItens(tipoRebeldeEnum)
                         .forEach(it -> {
                             Optional.of(rebelde.possuiItem(it.getNome()))
                                     .filter(Predicate.isEqual(true))
                                     .orElseThrow(() -> new BusinessException(tipoRebeldeEnum.getMensagemNaoPossuiItem()));
                             Optional.of(rebelde.possuiQuantidade(it.getNome(), it.getQuantidade()))
                                     .filter(Predicate.isEqual(true))
                                     .orElseThrow(() -> new BusinessException(tipoRebeldeEnum.getMensagemNaoPossuiQuantidade()));
                         });
    }

    private void atualizarItensRebelde(Rebelde primeiroRebelde, Rebelde segundoRebelde, NegociacaoRequest negociacaoRequest){
        logger.debug("Atualizando os itens da negociacao entre os rebeldes");
        processadoresDeNegociacao.forEach(proc -> proc.processar(primeiroRebelde, segundoRebelde, negociacaoRequest));
    }

}