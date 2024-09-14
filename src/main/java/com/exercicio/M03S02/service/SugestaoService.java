package com.exercicio.M03S02.service;

import com.exercicio.M03S02.entities.Comentario;
import com.exercicio.M03S02.entities.DataTransfer.ComentarioRequestDTO;
import com.exercicio.M03S02.entities.DataTransfer.SugestaoRequestDTO;
import com.exercicio.M03S02.entities.DataTransfer.SugestaoResponseDTO;
import com.exercicio.M03S02.entities.Sugestao;
import com.exercicio.M03S02.repository.ComentarioRepo;
import com.exercicio.M03S02.repository.SugestaoRepo;
import com.exercicio.M03S02.service.interfaces.SugestaoInterface;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@Service
public class SugestaoService implements SugestaoInterface {

    private final SugestaoRepo repository;
    public final ComentarioRepo comentarioRepo;
    public SugestaoService(SugestaoRepo repository, ComentarioRepo comentarioRepo) {
        this.repository = repository;
        this.comentarioRepo = comentarioRepo;
    }


    @Override
    public SugestaoResponseDTO cadastrarSugestao(SugestaoRequestDTO novaSugestao) {
        Sugestao nova =  new Sugestao(novaSugestao.getTitulo(), novaSugestao.getDescricao());
        repository.save(nova);
        return new SugestaoResponseDTO(nova);
    }

    @Override
    public List<Sugestao> listarSugestoes() {
        if (repository.findAll().isEmpty()) {
            //logger.error("Lista vazia. Não há sugestões cadastradas.");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Não há sugestões cadastradas."
            );
        }
        //logger.info("Retornando listagem completa de sugestões cadastrados.");

        List<Sugestao> listaOrdenada = repository.findAll();
        Collections.sort(listaOrdenada);

        return listaOrdenada;
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

        //sugestao.adicionaComentario(comentario);
        comentarioRepo.save(novoComentario);
        sugestao.setDataAtualizacao();
        repository.save(sugestao);

        return novoComentario;
    }
}
