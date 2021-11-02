package com.resistence.network.application.service;

import com.resistence.network.application.builder.NegociacaoRequestBuilder;
import com.resistence.network.application.builder.RebeldeBuilder;
import com.resistence.network.application.dto.request.NegociacaoRequest;
import com.resistence.network.application.processor.NegociacaoProcessor;
import com.resistence.network.domain.exceptions.BusinessException;
import com.resistence.network.domain.exceptions.ErrorMessage;
import com.resistence.network.domain.exceptions.NotFoundException;
import com.resistence.network.infrastructure.repository.RebeldeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class NegociacaoServiceTest {

    @Mock
    private ItemService itemService;

    @Mock
    private RebeldeRepository rebeldeRepository;

    @Mock
    private List<NegociacaoProcessor> processadoresDeNegociacao;

    @InjectMocks
    private NegociacaoService negociacaoService;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void testBuscarPrimeiroRebeldeNaoEncontrado(){
        NegociacaoRequest negociacaoRequest = NegociacaoRequestBuilder.umaNegociacaoRequest().now();
        when(rebeldeRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> negociacaoService.negociarItens(negociacaoRequest), ErrorMessage.MSG_003.getDescription());
        verify(rebeldeRepository, times(1)).findById(anyLong());
    }

    @Test
    void testBuscarSegundoRebeldeNaoEncontrado(){
        NegociacaoRequest negociacaoRequest = NegociacaoRequestBuilder.umaNegociacaoRequest().now();
        when(rebeldeRepository.findById(1L)).thenReturn(Optional.of(RebeldeBuilder.umRebelde().now()));
        when(rebeldeRepository.findById(2L)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> negociacaoService.negociarItens(negociacaoRequest), ErrorMessage.MSG_004.getDescription());
        verify(rebeldeRepository, times(1)).findById(1L);
        verify(rebeldeRepository, times(1)).findById(2L);
    }

    @Test
    void testBuscarPrimeiroRebeldeTraidor(){
        NegociacaoRequest negociacaoRequest = NegociacaoRequestBuilder.umaNegociacaoRequest().now();
        when(rebeldeRepository.findById(anyLong())).thenReturn(Optional.of(RebeldeBuilder.umRebelde().setTraidor().now()));
        Assertions.assertThrows(BusinessException.class, () -> negociacaoService.negociarItens(negociacaoRequest), ErrorMessage.MSG_005.getDescription());
        verify(rebeldeRepository, times(1)).findById(anyLong());
    }

    @Test
    void testBuscarSegundoRebeldeTraidor(){
        NegociacaoRequest negociacaoRequest = NegociacaoRequestBuilder.umaNegociacaoRequest().now();
        when(rebeldeRepository.findById(1L)).thenReturn(Optional.of(RebeldeBuilder.umRebelde().now()));
        when(rebeldeRepository.findById(2L)).thenReturn(Optional.of(RebeldeBuilder.umRebelde().setTraidor().now()));
        Assertions.assertThrows(BusinessException.class, () -> negociacaoService.negociarItens(negociacaoRequest), ErrorMessage.MSG_006.getDescription());
        verify(rebeldeRepository, times(1)).findById(1L);
        verify(rebeldeRepository, times(1)).findById(2L);
    }

//    public void negociarItens(NegociacaoRequest negociacaoRequest){
//        logger.debug("Iniciando a negociação de itens entre os rebeldes {}", negociacaoRequest);
//        Rebelde primeiroRebelde = verificarRebeldeNegociacao(negociacaoRequest.getOidPrimeiroRebelde(), TipoRebeldeEnum.PRIMEIRO);
//        Rebelde segundoRebelde = verificarRebeldeNegociacao(negociacaoRequest.getOidSegundoRebelde(), TipoRebeldeEnum.SEGUNDO);
//        verificarQuantidadePontuacao(negociacaoRequest);
//        verificarItensRebeldeParaNegociacao(primeiroRebelde, negociacaoRequest, TipoRebeldeEnum.PRIMEIRO);
//        verificarItensRebeldeParaNegociacao(segundoRebelde, negociacaoRequest, TipoRebeldeEnum.SEGUNDO);
//        atualizarItensRebelde(primeiroRebelde, segundoRebelde, negociacaoRequest);
//    }
//
//    private void verificarQuantidadePontuacao(NegociacaoRequest negociacaoRequest){
//        logger.debug("Validando a pontuação dos itens dos rebeldes");
//        if(!negociacaoRequest.verificarPontuacaoDosItens(itemService.listarItens().stream().collect(Collectors.toMap(Item::getNome, Function.identity())))){
//            throw new BusinessException(ErrorMessage.MSG_011.getDescription());
//        }
//    }
//
//    private Rebelde verificarRebeldeNegociacao(Long oidRebelde, TipoRebeldeEnum tipoRebeldeEnum){
//        logger.debug("Validando o {} rebelde {}", tipoRebeldeEnum, oidRebelde);
//        return Optional.of(rebeldeRepository.findById(oidRebelde)
//                .orElseThrow(() -> new NotFoundException(tipoRebeldeEnum.getMensagemNaoEncontrado())))
//                .filter(Predicate.not(Rebelde::isTraidor))
//                .orElseThrow(() -> new BusinessException(tipoRebeldeEnum.getMensagemTraidor()));
//    }
//
//    private void verificarItensRebeldeParaNegociacao(Rebelde rebelde, NegociacaoRequest negociacaoRequest, TipoRebeldeEnum tipoRebeldeEnum){
//        logger.debug("Validando os itens do {} rebelde envolvido na negociacao", tipoRebeldeEnum);
//        negociacaoRequest.getItens(tipoRebeldeEnum)
//                .forEach(it -> {
//                    Optional.of(rebelde.possuiItem(it.getNome()))
//                            .filter(Predicate.isEqual(true))
//                            .orElseThrow(() -> new BusinessException(tipoRebeldeEnum.getMensagemNaoPossuiItem()));
//                    Optional.of(rebelde.possuiQuantidade(it.getNome(), it.getQuantidade()))
//                            .filter(Predicate.isEqual(true))
//                            .orElseThrow(() -> new BusinessException(tipoRebeldeEnum.getMensagemNaoPossuiQuantidade()));
//                });
//    }
//
//    private void atualizarItensRebelde(Rebelde primeiroRebelde, Rebelde segundoRebelde, NegociacaoRequest negociacaoRequest){
//        logger.debug("Atualizando os itens da negociacao entre os rebeldes");
//        processadoresDeNegociacao.forEach(proc -> proc.processar(primeiroRebelde, segundoRebelde, negociacaoRequest));
//    }

}