package com.exercicio.M03S02.service.interfaces;

import com.exercicio.M03S02.entities.Comentario;
import com.exercicio.M03S02.entities.DataTransfer.ComentarioRequestDTO;
import com.exercicio.M03S02.entities.DataTransfer.SugestaoRequestDTO;
import com.exercicio.M03S02.entities.DataTransfer.SugestaoResponseDTO;

import java.util.List;

public interface SugestaoInterface {

    SugestaoResponseDTO cadastrarSugestao(SugestaoRequestDTO novaSugestao);

    List<SugestaoResponseDTO> listarSugestoes();

    SugestaoResponseDTO obterSugestaoPorId(Long id);

    Comentario cadastrarComentario(Long id, ComentarioRequestDTO comentario);
}
