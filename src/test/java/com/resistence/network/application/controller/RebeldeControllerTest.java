package com.resistence.network.application.controller;

import com.resistence.network.application.builder.AtualizarLocalizacaoRequestBuilder;
import com.resistence.network.application.builder.RebeldeDtoBuilder;
import com.resistence.network.application.builder.RebeldeRequestBuilder;
import com.resistence.network.application.dto.request.AtualizarLocalizacaoRequest;
import com.resistence.network.application.dto.request.RebeldeRequest;
import com.resistence.network.application.service.RebeldeService;
import com.resistence.network.application.util.JsonUtil;
import com.resistence.network.domain.exceptions.ErrorMessage;
import com.resistence.network.domain.exceptions.NotFoundException;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RebeldeController.class)
class RebeldeControllerTest {

    private MockMvc mvc;

    @MockBean
    private RebeldeService rebeldeService;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        RebeldeController controller = new RebeldeController(rebeldeService);
        this.mvc = MockMvcBuilders.standaloneSetup(controller)
                                  .setCustomArgumentResolvers(pageableArgumentResolver)
                                  .setMessageConverters(jacksonMessageConverter)
                                  .setControllerAdvice(CustomExceptionHandler.class)
                                  .build();
    }

    @Test
    void testAdicionarRebeldeComSucesso() throws Exception {
        when(rebeldeService.inserirRebelde(any(RebeldeRequest.class))).thenReturn(RebeldeDtoBuilder.umRebeldeDto().now());
        mvc.perform(post("/v1/rebelde")
           .contentType(MediaType.APPLICATION_JSON_VALUE)
           .content(JsonUtil.convertObjectToJsonBytes(RebeldeRequestBuilder.umRebeldeRequest().now())))
           .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void testListarComSucesso() throws Exception {
        when(rebeldeService.listarRebeldes()).thenReturn(List.of(RebeldeDtoBuilder.umRebeldeDto().now()));
        mvc.perform(get("/v1/rebelde")
           .accept(MediaType.APPLICATION_JSON_VALUE))
           .andExpect(status().isOk());
    }

    @Test
    void testBuscarComSucesso() throws Exception {
        when(rebeldeService.buscarRebelde(1L)).thenReturn(RebeldeDtoBuilder.umRebeldeDto().now());
        mvc.perform(get("/v1/rebelde/{id}", 1L)
           .accept(MediaType.APPLICATION_JSON_VALUE))
           .andExpect(status().isOk());
    }

    @Test
    void testBuscarComFalha() throws Exception {
        doThrow(new NotFoundException(ErrorMessage.MSG_002.getDescription())).when(rebeldeService).buscarRebelde(anyLong());
        mvc.perform(get("/v1/rebelde/{id}", 1L)
           .accept(MediaType.APPLICATION_JSON_VALUE))
           .andExpect(status().isNotFound());
    }

    @Test
    void testAtualizarLocalizacaoComSucesso() throws Exception {
        when(rebeldeService.atualizarLocalizacao(any(AtualizarLocalizacaoRequest.class))).thenReturn(RebeldeDtoBuilder.umRebeldeDto().now());
        mvc.perform(put("/v1/rebelde/localizacao")
           .contentType(MediaType.APPLICATION_JSON_VALUE)
           .content(JsonUtil.convertObjectToJsonBytes(AtualizarLocalizacaoRequestBuilder.umaAtualizarLocalizacaoRequest().now())))
           .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testAtualizarLocalizacaoComFalha() throws Exception {
        doThrow(new NotFoundException(ErrorMessage.MSG_002.getDescription())).when(rebeldeService).atualizarLocalizacao(any(AtualizarLocalizacaoRequest.class));
        mvc.perform(put("/v1/rebelde/localizacao")
           .contentType(MediaType.APPLICATION_JSON_VALUE)
           .content(JsonUtil.convertObjectToJsonBytes(AtualizarLocalizacaoRequestBuilder.umaAtualizarLocalizacaoRequest().now())))
           .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testRelatarTraicaoComSucesso() throws Exception {
        when(rebeldeService.relatarTraicao(anyLong())).thenReturn(RebeldeDtoBuilder.umRebeldeDto().now());
        mvc.perform(put("/v1/rebelde/{id}", 1L)
           .contentType(MediaType.APPLICATION_JSON_VALUE))
           .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testRelatarTraicaoComFalha() throws Exception {
        when(rebeldeService.relatarTraicao(anyLong())).thenReturn(RebeldeDtoBuilder.umRebeldeDto().now());
        doThrow(new NotFoundException(ErrorMessage.MSG_002.getDescription())).when(rebeldeService).relatarTraicao(anyLong());
        mvc.perform(put("/v1/rebelde/{id}", 1L)
           .contentType(MediaType.APPLICATION_JSON_VALUE))
           .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
