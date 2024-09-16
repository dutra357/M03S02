package com.exercicio.M03S02.controller;
import com.exercicio.M03S02.entities.Comentario;
import com.exercicio.M03S02.entities.DataTransfer.ComentarioRequestDTO;
import com.exercicio.M03S02.entities.DataTransfer.SugestaoRequestDTO;
import com.exercicio.M03S02.entities.DataTransfer.SugestaoResponseDTO;
import com.exercicio.M03S02.service.SugestaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping(value = "/sugestoes", produces = {"application/json"})
@Tag(name = "API - SENAI M03S02")
public class SugestaoController {

    private static final Logger logger = LoggerFactory.getLogger(SugestaoController.class);
    public final SugestaoService service;
    public SugestaoController(SugestaoService service) {
        this.service = service;
    }


    @Operation(summary = "Cadastrar SUGESTÃO", description = "Realiza o cadastramento de uma nova Sugestão", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso.",
                    content = @Content(schema = @Schema(implementation = SugestaoResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos.",
                    content = @Content(schema = @Schema())),
    })

    @PostMapping
    public ResponseEntity<SugestaoResponseDTO> cadastrarSugestao(@RequestBody @Valid SugestaoRequestDTO novaSugestao) {
        logger.info("Solicitado o cadastramento de nova Sugestão.");
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(service.cadastrarSugestao(novaSugestao));
    }

    @Operation(summary = "Listar TODAS" ,description = "Lista todas as Sugestões cadastradas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno da lista com sucesso."),
            @ApiResponse(responseCode = "404", description = "Não há Sugestões cadastradas."),
    })
    @GetMapping
    public ResponseEntity<List<SugestaoResponseDTO>> listarSugestoes() {
        logger.info("Solicitada listagem de Sugestões.");
        return ResponseEntity.status(HttpStatus.OK).body(service.listarSugestoes());
    }

    @Operation(summary = "Busca/ID", description = "Busca uma Sugestão por ID", method = "GET/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a Sugestão solicitada com a lista de comentários."),
            @ApiResponse(responseCode = "404", description = "Sugestão não encontrada.",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("{id}")
    public ResponseEntity<SugestaoResponseDTO> obterSugestaoPorId(@Parameter(description = "ID da Sugestão") @PathVariable Long id) {
        logger.info("Solicitado Sugestão por ID, {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.obterSugestaoPorId(id));
    }

    @Operation(summary = "Cadastrar COMENTÁRIO", description = "Cadastra um comentário em certa (/{id}/comentario) Sugestão.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comenário inserido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Sugestão não encontrada.",
                    content = @Content(schema = @Schema())),
    })
    @PostMapping("{id}/comentario")
    public ResponseEntity<Comentario> cadastrarComentario(@PathVariable @Valid Long id, @RequestBody @Valid ComentarioRequestDTO novoComentario) {
        logger.info("Cadastrando Comentário.");
        return ResponseEntity.status(HttpStatus.OK).body(service.cadastrarComentario(id, novoComentario));
    }
}
