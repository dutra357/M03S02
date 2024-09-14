package com.exercicio.M03S02.entities.DataTransfer;

public class ComentarioRequestDTO {

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
