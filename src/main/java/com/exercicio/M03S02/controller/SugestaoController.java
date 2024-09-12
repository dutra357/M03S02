package com.exercicio.M03S02.controller;
import com.exercicio.M03S02.entities.Comentario;
import com.exercicio.M03S02.entities.DataTransfer.SugestaoResponseDTO;
import com.exercicio.M03S02.entities.Sugestao;
import com.exercicio.M03S02.service.SugestaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sugestoes", produces = {"application/json"})
@Tag(name = "API - SENAI M03S02")
public class SugestaoController {

    public final SugestaoService service;
    public SugestaoController(SugestaoService service) {
        this.service = service;
    }


    @Operation(summary = "Realiza o cadastramento de uma nova Sugestão", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos."),
    })
    @PostMapping
    public ResponseEntity<Sugestao> cadastrarSugestao(@RequestBody Sugestao novaSugestao) {
        // logger.info("Solicitado o cadastramento de nono Aluno.");
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(service.cadastrarSugestao(novaSugestao));
    }


    @Operation(summary = "Lista todas as Sugestões cadastradas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno da lista com sucesso."),
            @ApiResponse(responseCode = "404", description = "Não há Sugestões cadastradas."),
    })
    @GetMapping
    public ResponseEntity<List<Sugestao>> listarSugestoes() {
        //logger.info("Solicitada listagem completa de Cursos cadastrados no sistema.");
        return ResponseEntity.status(HttpStatus.OK).body(service.listarSugestoes());
    }


    @Operation(summary = "Busca uma Sugestão por ID", method = "GET/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a Sugestão solicitada."),
            @ApiResponse(responseCode = "404", description = "Sugestão não encontrada."),
    })
    @GetMapping("{id}")
    public ResponseEntity<SugestaoResponseDTO> obterSugestaoPorId(@PathVariable Long id) {
        //logger.info("Solicitado dados do Curso ID {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.obterSugestaoPorId(id));
    }


    @Operation(summary = "Cadastra um comentário em certa (/{id}/comentario) Sugestão.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comenário inserido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Sugestão não encontrada."),
    })
    @PostMapping("{id}/comentario")
    public ResponseEntity<Comentario> cadastrarComentario(@PathVariable Long id, @RequestBody Comentario novoComentario) {
        //logger.info("Solicitado dados do Curso ID {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.cadastrarComentario(id, novoComentario));
    }
}
