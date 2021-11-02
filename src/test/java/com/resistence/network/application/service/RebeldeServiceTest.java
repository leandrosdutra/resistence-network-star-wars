package com.resistence.network.application.service;

import com.resistence.network.application.builder.*;
import com.resistence.network.application.dto.RebeldeDto;
import com.resistence.network.application.dto.request.AtualizarLocalizacaoRequest;
import com.resistence.network.application.dto.request.RebeldeRequest;
import com.resistence.network.domain.Localizacao;
import com.resistence.network.domain.Rebelde;
import com.resistence.network.domain.enums.GeneroEnum;
import com.resistence.network.domain.exceptions.ErrorMessage;
import com.resistence.network.domain.exceptions.NotFoundException;
import com.resistence.network.infrastructure.repository.LocalizacaoRepository;
import com.resistence.network.infrastructure.repository.RebeldeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RebeldeServiceTest {

    @Mock
    private RebeldeRepository rebeldeRepository;

    @Mock
    private LocalizacaoRepository localizacaoRepository;

    @Mock
    private ItemService itemService;

    @InjectMocks
    private RebeldeService rebeldeService;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void testBuscarRebeldeComSucesso(){
        when(rebeldeRepository.findById(anyLong())).thenReturn(Optional.of(RebeldeBuilder.umRebelde().now()));
        RebeldeDto rebeldeDto = rebeldeService.buscarRebelde(1L);
        Assertions.assertNotNull(rebeldeDto);
        Assertions.assertEquals("Chewbacca", rebeldeDto.getNome());
        Assertions.assertEquals(30, rebeldeDto.getIdade());
        Assertions.assertEquals(0, rebeldeDto.getQtdTraicoes());
        Assertions.assertEquals(GeneroEnum.MASCULINO, rebeldeDto.getGenero());
        verify(rebeldeRepository, times(1)).findById(anyLong());
    }

    @Test
    void testBuscarRebeldeComFalha(){
        when(rebeldeRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> rebeldeService.buscarRebelde(1L), ErrorMessage.MSG_002.getDescription());
        verify(rebeldeRepository, times(1)).findById(anyLong());
    }

    @Test
    void testListarRebeldesComSucesso(){
        when(rebeldeRepository.findAll()).thenReturn(List.of(RebeldeBuilder.umRebelde().now(), RebeldeBuilder.umRebelde().now()));
        List<RebeldeDto> rebeldeDtoList = rebeldeService.listarRebeldes();
        Assertions.assertNotNull(rebeldeDtoList);
        Assertions.assertEquals(2, rebeldeDtoList.size());
        verify(rebeldeRepository, times(1)).findAll();
    }

    @Test
    void testInserirRebeldeComSucesso(){
        when(itemService.listarItens()).thenReturn(List.of(ItemBuilder.umItem().comNome("Arma").comPontuacao(4).now(),
                                                           ItemBuilder.umItem().comNome("Municao").comPontuacao(3).now(),
                                                           ItemBuilder.umItem().comNome("Agua").comPontuacao(2).now(),
                                                           ItemBuilder.umItem().comNome("Comida").comPontuacao(1).now()));
        when(itemService.validarItensTrocados(anyList(), anyMap())).thenReturn(true);
        when(rebeldeRepository.save(any(Rebelde.class))).thenReturn(RebeldeBuilder.umRebelde().now());
        when(localizacaoRepository.save(any(Localizacao.class))).thenReturn(LocalizacaoBuilder.umaLocalizacao().now());
        when(itemService.salvarItens(anyList())).thenReturn(List.of(RebeldeItemBuilder.umRebeldeItem().now()));
        RebeldeDto rebeldeDto = rebeldeService.inserirRebelde(RebeldeRequestBuilder.umRebeldeRequest().now());
        Assertions.assertNotNull(rebeldeDto);
        Assertions.assertEquals("Chewbacca", rebeldeDto.getNome());
        Assertions.assertEquals(GeneroEnum.MASCULINO, rebeldeDto.getGenero());
        verify(itemService, times(1)).listarItens();
        verify(itemService, times(1)).validarItensTrocados(anyList(), anyMap());
        verify(rebeldeRepository, times(1)).save(any(Rebelde.class));
        verify(localizacaoRepository, times(1)).save(any(Localizacao.class));
        verify(itemService, times(1)).salvarItens(anyList());
    }

    @Test
    void testInserirRebeldeComItemInexistente() {
        when(itemService.listarItens()).thenReturn(List.of(ItemBuilder.umItem().comNome("Arma").comPontuacao(4).now(),
                                                           ItemBuilder.umItem().comNome("Municao").comPontuacao(3).now(),
                                                           ItemBuilder.umItem().comNome("Agua").comPontuacao(2).now(),
                                                           ItemBuilder.umItem().comNome("Comida").comPontuacao(1).now()));
        when(itemService.validarItensTrocados(anyList(), anyMap())).thenReturn(false);
        RebeldeRequest request = RebeldeRequestBuilder.umRebeldeRequest().now();
        Assertions.assertThrows(NotFoundException.class, () -> rebeldeService.inserirRebelde(request), ErrorMessage.MSG_013.getDescription());
        verify(itemService, times(1)).listarItens();
        verify(itemService, times(1)).validarItensTrocados(anyList(), anyMap());
        verify(rebeldeRepository, never()).save(any(Rebelde.class));
        verify(localizacaoRepository, never()).save(any(Localizacao.class));
        verify(itemService, never()).salvarItens(anyList());
    }

    @Test
    void testAtualizarLocalizacaoComSucesso(){
        when(rebeldeRepository.findById(anyLong())).thenReturn(Optional.of(RebeldeBuilder.umRebelde().now()));
        when(rebeldeRepository.save(any(Rebelde.class))).thenReturn(RebeldeBuilder.umRebelde().now());
        RebeldeDto rebeldeDto = rebeldeService.atualizarLocalizacao(AtualizarLocalizacaoRequestBuilder.umaAtualizarLocalizacaoRequest().now());
        Assertions.assertNotNull(rebeldeDto);
        Assertions.assertNotNull(rebeldeDto.getLocalizacao());
        Assertions.assertEquals("Via Lactea", rebeldeDto.getLocalizacao().getNome());
        verify(rebeldeRepository, times(1)).findById(anyLong());
        verify(rebeldeRepository, times(1)).save(any(Rebelde.class));
    }

    @Test
    void testAtualizarLocalizacaoComRebeldeNaoEncontrado(){
        when(rebeldeRepository.findById(anyLong())).thenReturn(Optional.empty());
        AtualizarLocalizacaoRequest request = AtualizarLocalizacaoRequestBuilder.umaAtualizarLocalizacaoRequest().now();
        Assertions.assertThrows(NotFoundException.class, () -> rebeldeService.atualizarLocalizacao(request), ErrorMessage.MSG_002.getDescription());
        verify(rebeldeRepository, times(1)).findById(anyLong());
        verify(rebeldeRepository, never()).save(any(Rebelde.class));
    }

    @Test
    void testRelatarTraicaoComSucesso(){
        Rebelde rebelde = RebeldeBuilder.umRebelde().now();
        when(rebeldeRepository.findById(anyLong())).thenReturn(Optional.of(rebelde));
        rebelde.setQtdTraicoes(rebelde.getQtdTraicoes() + 1);
        when(rebeldeRepository.save(any(Rebelde.class))).thenReturn(rebelde);
        RebeldeDto rebeldeDto = rebeldeService.relatarTraicao(1L);
        Assertions.assertNotNull(rebeldeDto);
        Assertions.assertEquals(rebelde.getQtdTraicoes(), rebeldeDto.getQtdTraicoes());
        verify(rebeldeRepository, times(1)).findById(anyLong());
        verify(rebeldeRepository, times(1)).save(any(Rebelde.class));
    }

    @Test
    void testRelatarTraicaoComRebeldeNaoEncontrado(){
        when(rebeldeRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> rebeldeService.relatarTraicao(1L), ErrorMessage.MSG_002.getDescription());
        verify(rebeldeRepository, times(1)).findById(anyLong());
        verify(rebeldeRepository, never()).save(any(Rebelde.class));
    }

}