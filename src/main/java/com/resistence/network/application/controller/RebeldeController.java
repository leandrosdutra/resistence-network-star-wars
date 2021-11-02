package com.resistence.network.application.controller;

import com.resistence.network.application.dto.RebeldeDto;
import com.resistence.network.application.dto.request.AtualizarLocalizacaoRequest;
import com.resistence.network.application.dto.request.RebeldeRequest;
import com.resistence.network.application.service.RebeldeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import java.util.List;

@RestController
public class RebeldeController {

    private final RebeldeService rebeldeService;

    public RebeldeController(RebeldeService rebeldeService) {
        this.rebeldeService = rebeldeService;
    }

    @PostMapping("v1/rebelde")
    @ApiOperation(value = "Adicionar um novo rebelde")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Rebelde adicionado com sucesso"),
            @ApiResponse(code = 500, message = "Erro interno")})
    public ResponseEntity<RebeldeDto> adicionar(@Valid @RequestBody RebeldeRequest rebeldeRequest) {
        return new ResponseEntity<>(rebeldeService.inserirRebelde(rebeldeRequest), HttpStatus.CREATED);
    }

    @GetMapping("v1/rebelde")
    @ApiOperation(value = "Listar todos os rebeldes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Rebeldes listados com sucesso"),
            @ApiResponse(code = 500, message = "Erro interno")})
    public ResponseEntity<List<RebeldeDto>> listar() {
        return ResponseEntity.ok(rebeldeService.listarRebeldes());
    }

    @GetMapping("v1/rebelde/{id}")
    @ApiOperation(value = "Buscar um rebelde")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Rebelde encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Rebelde não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")})
    public ResponseEntity<RebeldeDto> buscar(@PathVariable("id") Long id) {
        return ResponseEntity.ok(rebeldeService.buscarRebelde(id));
    }

    @PutMapping("v1/rebelde/localizacao")
    @ApiOperation(value = "Atualizar a localização de um rebelde")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Localização atualizada com sucesso"),
            @ApiResponse(code = 404, message = "Rebelde não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")})
    public ResponseEntity<RebeldeDto> atualizarLocalizacao(@Valid @RequestBody AtualizarLocalizacaoRequest request) {
        return new ResponseEntity<>(rebeldeService.atualizarLocalizacao(request), HttpStatus.OK);
    }

    @PutMapping("v1/rebelde/{id}")
    @ApiOperation(value = "Relatar a traição de um rebelde")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Relato de traição efetivado com sucesso"),
            @ApiResponse(code = 404, message = "Rebelde não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno")})
    public ResponseEntity<RebeldeDto> relatarTraicao(@PathVariable("id") Long id) {
        return new ResponseEntity<>(rebeldeService.relatarTraicao(id), HttpStatus.OK);
    }

}