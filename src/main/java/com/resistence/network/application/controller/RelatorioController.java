package com.resistence.network.application.controller;

import com.resistence.network.application.dto.DadosRelatorioDto;
import com.resistence.network.application.dto.RebeldeDto;
import com.resistence.network.application.service.RelatorioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("v1/relatorio")
    @ApiOperation(value = "Exibir dados do relatório")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Relatório exibido com sucesso"),
            @ApiResponse(code = 500, message = "Erro interno")})
    public ResponseEntity<DadosRelatorioDto> exibirRelatorio() {
        return ResponseEntity.ok(relatorioService.buscarDadosRelatorio());
    }

}