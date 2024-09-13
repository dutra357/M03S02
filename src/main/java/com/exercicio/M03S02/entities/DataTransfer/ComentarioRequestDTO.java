package com.exercicio.M03S02.entities.DataTransfer;

public class ComentarioRequestDTO {

    private String comentario;
    private Long sugestaoId;

    public ComentarioRequestDTO() {}
    public ComentarioRequestDTO(String comentario, Long sugestaoId) {
        this.comentario = comentario;
        this.sugestaoId = sugestaoId;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Long getSugestaoId() {
        return sugestaoId;
    }

    public void setSugestaoId(Long sugestaoId) {
        this.sugestaoId = sugestaoId;
    }
}
