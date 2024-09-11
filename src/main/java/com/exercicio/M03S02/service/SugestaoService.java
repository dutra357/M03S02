package com.exercicio.M03S02.service;

import com.exercicio.M03S02.entities.Sugestao;
import com.exercicio.M03S02.repository.SugestaoRepo;
import com.exercicio.M03S02.service.interfaces.SugestaoInterface;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SugestaoService implements SugestaoInterface {

    private final SugestaoRepo repository;

    public SugestaoService(SugestaoRepo repository) {
        this.repository = repository;
    }

    @Override
    public Sugestao cadastrarSugestao(Sugestao novaSugestao) {
        return repository.save(novaSugestao);
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
    public Sugestao obterSugestaoPorId(Long id) {
        if (!repository.existsById(id)) {
            //logger.error("Sugestão não encontrada, ID info: {}", id);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Sugestão não encontrada."
            );
        }
        //logger.info("Retornando Curso solicidato, ID {}", id);
        return repository.findById(id).get();
    }
}
