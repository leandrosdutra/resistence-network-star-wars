package com.resistence.network.application.controller;

import com.resistence.network.application.builder.DadosRelatorioDtoBuilder;
import com.resistence.network.application.service.RelatorioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RelatorioController.class)
class RelatorioControllerTest {

    private MockMvc mvc;

    @MockBean
    private RelatorioService relatorioService;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        RelatorioController controller = new RelatorioController(relatorioService);
        this.mvc = MockMvcBuilders.standaloneSetup(controller)
                                  .setCustomArgumentResolvers(pageableArgumentResolver)
                                  .setMessageConverters(jacksonMessageConverter)
                                  .setControllerAdvice(CustomExceptionHandler.class)
                                  .build();
    }

    @Test
    void testExibirRelatorioComSucesso() throws Exception {
        when(relatorioService.buscarDadosRelatorio()).thenReturn(DadosRelatorioDtoBuilder.umDadosRelatorioDto().now());
        mvc.perform(get("/v1/relatorio")
           .accept(MediaType.APPLICATION_JSON_VALUE))
           .andExpect(status().isOk());
    }

}