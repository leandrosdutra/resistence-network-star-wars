package com.resistence.network.application.service;

import com.resistence.network.application.builder.RebeldeBuilder;
import com.resistence.network.application.dto.DadosRelatorioDto;
import com.resistence.network.infrastructure.repository.RebeldeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.mockito.Mockito.*;

class RelatorioServiceTest {

    @Mock
    private RebeldeRepository rebeldeRepository;

    @InjectMocks
    private RelatorioService relatorioService;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void testBuscarDadosRelatorio50PorCentoComSucesso(){
        when(rebeldeRepository.findAll()).thenReturn(List.of(RebeldeBuilder.umRebelde().now(), RebeldeBuilder.umRebelde().setTraidor().now()));
        DadosRelatorioDto dadosRelatorioDto = relatorioService.buscarDadosRelatorio();
        Assertions.assertNotNull(dadosRelatorioDto);
        Assertions.assertEquals("50,00%", dadosRelatorioDto.getPercentualRebeldes());
        Assertions.assertEquals("50,00%", dadosRelatorioDto.getPercentualTraidores());
        verify(rebeldeRepository, times(1)).findAll();
    }

    @Test
    void testBuscarDadosRelatorio75PorCentoComSucesso(){
        when(rebeldeRepository.findAll()).thenReturn(List.of(RebeldeBuilder.umRebelde().now(), RebeldeBuilder.umRebelde().now(),
                                                             RebeldeBuilder.umRebelde().now(), RebeldeBuilder.umRebelde().setTraidor().now()));
        DadosRelatorioDto dadosRelatorioDto = relatorioService.buscarDadosRelatorio();
        Assertions.assertNotNull(dadosRelatorioDto);
        Assertions.assertEquals("75,00%", dadosRelatorioDto.getPercentualRebeldes());
        Assertions.assertEquals("25,00%", dadosRelatorioDto.getPercentualTraidores());
        verify(rebeldeRepository, times(1)).findAll();
    }

}