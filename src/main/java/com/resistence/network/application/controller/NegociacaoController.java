package com.resistence.network.application.controller;

import com.resistence.network.application.dto.request.NegociacaoRequest;
import com.resistence.network.application.service.NegociacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
public class NegociacaoController {

    private final NegociacaoService negociacaoService;

    public NegociacaoController(NegociacaoService negociacaoService) {
        this.negociacaoService = negociacaoService;
    }

    @PostMapping("v1/negociacao")
    @ApiOperation(value = "Adicionar uma nova negociação entre rebeldes")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Negociação entre os rebeldes efetuada com sucesso"),
            @ApiResponse(code = 500, message = "Erro interno")})
    public ResponseEntity<Void> negociar(@Valid @RequestBody NegociacaoRequest negociacaoRequest) {
        negociacaoService.negociarItens(negociacaoRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}