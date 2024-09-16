package com.exercicio.M03S02.entities.DataTransfer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ComentarioRequestDTO {

    @NotBlank
    @NotEmpty
    @NotNull
    private String comentario;

    public ComentarioRequestDTO() {}
    public ComentarioRequestDTO(String comentario, Long sugestaoId) {
        this.comentario = comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
