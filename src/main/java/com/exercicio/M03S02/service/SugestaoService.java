package com.exercicio.M03S02.service;

import com.exercicio.M03S02.entities.Comentario;
import com.exercicio.M03S02.entities.DataTransfer.ComentarioRequestDTO;
import com.exercicio.M03S02.entities.DataTransfer.SugestaoRequestDTO;
import com.exercicio.M03S02.entities.DataTransfer.SugestaoResponseDTO;
import com.exercicio.M03S02.entities.Sugestao;
import com.exercicio.M03S02.repository.ComentarioRepo;
import com.exercicio.M03S02.repository.SugestaoRepo;
import com.exercicio.M03S02.service.interfaces.SugestaoInterface;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SugestaoService implements SugestaoInterface {

    private final SugestaoRepo repository;
    public final ComentarioRepo comentarioRepo;
    public SugestaoService(SugestaoRepo repository, ComentarioRepo comentarioRepo) {
        this.repository = repository;
        this.comentarioRepo = comentarioRepo;
    }


    @Override
    @Transactional
    public SugestaoResponseDTO cadastrarSugestao(SugestaoRequestDTO novaSugestao) {
        Sugestao nova =  new Sugestao(novaSugestao.getTitulo(), novaSugestao.getDescricao());
        repository.save(nova);
        return new SugestaoResponseDTO(nova);
    }

    @Override
    public List<SugestaoResponseDTO> listarSugestoes() {
        if (repository.findAll().isEmpty()) {
            //logger.error("Lista vazia. Não há sugestões cadastradas.");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não há sugestões cadastradas."
            );
        }
        //logger.info("Retornando listagem completa de sugestões cadastrados.");

        List<Sugestao> listaOrdenada = repository.findAll();
        Collections.sort(listaOrdenada);

        List<SugestaoResponseDTO> responseList = new ArrayList<>();

        for (Sugestao sugestao : listaOrdenada) {
            SugestaoResponseDTO convertida = new SugestaoResponseDTO(sugestao);
            responseList.add(convertida);
        }

        return responseList;
    }

    @Override
    public SugestaoResponseDTO obterSugestaoPorId(Long id) {
        if (!repository.existsById(id)) {
            //logger.error("Sugestão não encontrada, ID info: {}", id);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Sugestão não encontrada."
            );
        }
        //logger.info("Retornando Curso solicidato, ID {}", id);

        Sugestao encontrada = repository.findById(id).get();
        SugestaoResponseDTO resposta = new SugestaoResponseDTO(encontrada);

        var ordenados = encontrada.getComentarios();
        Collections.sort(ordenados);
        resposta.setComentarios(ordenados);

        return resposta;
    }

    @Override
    @Transactional
    public Comentario cadastrarComentario(Long id, ComentarioRequestDTO comentario) {
        if (!repository.existsById(id)) {
            //logger.error("Sugestão não encontrada, ID info: {}", id);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Sugestão não encontrada."
            );
        }
        //logger.info("Retornando Curso solicidato, ID {}", id);


        Sugestao sugestao = repository.findById(id).get();
        Comentario novoComentario = new Comentario(sugestao, comentario.getComentario());

        comentarioRepo.save(novoComentario);
        sugestao.setDataAtualizacao();
        repository.save(sugestao);

        return novoComentario;
    }
}
