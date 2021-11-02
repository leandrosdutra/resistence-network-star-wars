package com.resistence.network.application.controller;

import com.resistence.network.application.builder.NegociacaoRequestBuilder;
import com.resistence.network.application.dto.request.NegociacaoRequest;
import com.resistence.network.application.service.NegociacaoService;
import com.resistence.network.application.util.JsonUtil;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(NegociacaoController.class)
class NegociacaoControllerTest {

    private MockMvc mvc;

    @MockBean
    private NegociacaoService negociacaoService;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        NegociacaoController controller = new NegociacaoController(negociacaoService);
        this.mvc = MockMvcBuilders.standaloneSetup(controller)
                                  .setCustomArgumentResolvers(pageableArgumentResolver)
                                  .setMessageConverters(jacksonMessageConverter)
                                  .setControllerAdvice(CustomExceptionHandler.class)
                                  .build();
    }

    @Test
    void testAdicionarRebeldeComSucesso() throws Exception {
        doNothing().when(negociacaoService).negociarItens(any(NegociacaoRequest.class));
        mvc.perform(post("/v1/negociacao")
           .contentType(MediaType.APPLICATION_JSON_VALUE)
           .content(JsonUtil.convertObjectToJsonBytes(NegociacaoRequestBuilder.umaNegociacaoRequest().now())))
           .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}