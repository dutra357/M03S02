package com.exercicio.M03S02.controller;

import com.exercicio.M03S02.entities.Comentario;
import com.exercicio.M03S02.entities.DataTransfer.SugestaoResponseDTO;
import com.exercicio.M03S02.entities.Sugestao;
import com.exercicio.M03S02.service.SugestaoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sugestoes")
public class SugestaoController {

    public final SugestaoService service;
    public SugestaoController(SugestaoService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Sugestao> cadastrarSugestao(@RequestBody Sugestao novaSugestao) {
        // logger.info("Solicitado o cadastramento de nono Aluno.");
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(service.cadastrarSugestao(novaSugestao));
    }


    @GetMapping
    public ResponseEntity<List<Sugestao>> listarSugestoes() {
        //logger.info("Solicitada listagem completa de Cursos cadastrados no sistema.");
        return ResponseEntity.status(HttpStatus.OK).body(service.listarSugestoes());
    }

    @GetMapping("{id}")
    public ResponseEntity<SugestaoResponseDTO> obterSugestaoPorId(@PathVariable Long id) {
        //logger.info("Solicitado dados do Curso ID {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.obterSugestaoPorId(id));
    }

    @PostMapping("{id}/comentario")
    public ResponseEntity<Comentario> cadastrarComentario(@PathVariable Long id, @RequestBody Comentario novoComentario) {
        //logger.info("Solicitado dados do Curso ID {}.", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.cadastrarComentario(id, novoComentario));
    }
}
