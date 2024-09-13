package com.exercicio.M03S02.entities.DataTransfer;

import com.exercicio.M03S02.entities.Comentario;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public class SugestaoRequestDTO {

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;


    public SugestaoRequestDTO() {}
    public SugestaoRequestDTO(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
