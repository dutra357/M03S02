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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SugestaoService implements SugestaoInterface {

    private static final Logger logger = LoggerFactory.getLogger(SugestaoService.class);

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
        logger.info("Sugestão cadastrada com sucesso.");
        return new SugestaoResponseDTO(nova);
    }

    @Override
    public List<SugestaoResponseDTO> listarSugestoes() {
        if (repository.findAll().isEmpty()) {
            logger.error("Lista vazia. Não há sugestões cadastradas.");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não há sugestões cadastradas."
            );
        }
        logger.info("Retornando listagem completa de sugestões cadastrados.");

        List<Sugestao> listaDb = repository.findAll();

        List<SugestaoResponseDTO> responseList = new ArrayList<>();
        for (Sugestao sugestao : listaDb) {
            SugestaoResponseDTO convertida = new SugestaoResponseDTO(sugestao);

            var ordenados = convertida.getComentarios().stream().sorted(Comparator.comparing(Comentario::getDataEnvio).reversed()).collect(Collectors.toCollection(LinkedHashSet::new));
            convertida.setComentarios(ordenados);

            responseList.add(convertida);
        }
        responseList.sort(Collections.reverseOrder());
        return responseList;
    }

    @Override
    public SugestaoResponseDTO obterSugestaoPorId(Long id) {
        if (!repository.existsById(id)) {
            logger.error("Sugestão não encontrada, ID info: {}", id);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Sugestão não encontrada."
            );
        }
        logger.info("Retornando Sugestão solicitada, ID {}", id);

        Sugestao encontrada = repository.findById(id).get();

        SugestaoResponseDTO resposta = new SugestaoResponseDTO(encontrada);

        var ordenados = encontrada.getComentarios().stream().sorted(Comparator.comparing(Comentario::getDataEnvio).reversed()).collect(Collectors.toCollection(LinkedHashSet::new));
        resposta.setComentarios(ordenados);

        return resposta;
    }

    @Override
    @Transactional
    public Comentario cadastrarComentario(Long id, ComentarioRequestDTO comentario) {
        if (!repository.existsById(id)) {
            logger.error("Não há Sugestão a ser vinculada (não encontrada), ID info: {}", id);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Sugestão não encontrada."
            );
        }

        Sugestao sugestao = repository.findById(id).get();
        Comentario novoComentario = new Comentario(sugestao, comentario.getComentario());

        comentarioRepo.save(novoComentario);
        sugestao.setDataAtualizacao();
        repository.save(sugestao);

        logger.info("Comentário cadastrado com sucesso!");

        return novoComentario;
    }
}
