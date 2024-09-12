package com.exercicio.M03S02.service.interfaces;

import com.exercicio.M03S02.entities.Comentario;
import com.exercicio.M03S02.entities.DataTransfer.SugestaoResponseDTO;
import com.exercicio.M03S02.entities.Sugestao;

import java.util.List;

public interface SugestaoInterface {

    Sugestao cadastrarSugestao(Sugestao novaSugestao);

    List<Sugestao> listarSugestoes();

    SugestaoResponseDTO obterSugestaoPorId(Long id);

    Comentario cadastrarComentario(Long id, Comentario comentario);
}
