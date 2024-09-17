package com.exercicio.M03S02.entities.DataTransfer;

import com.exercicio.M03S02.entities.Comentario;
import com.exercicio.M03S02.entities.Sugestao;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class SugestaoResponseDTO implements Comparable<SugestaoResponseDTO> {

    private long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataEnvio;
    private LocalDateTime dataAtualizacao;


    private Set<Comentario> comentarios;


    public SugestaoResponseDTO() {
    }
    public SugestaoResponseDTO(long id, String titulo, String descricao, LocalDateTime dataEnvio, LocalDateTime dataAtualizacao, Set<Comentario> comentarios) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataEnvio = dataEnvio;
        this.dataAtualizacao = dataAtualizacao;
        this.comentarios = comentarios;
    }

    public SugestaoResponseDTO(Sugestao sugestao) {
        this.id = sugestao.getId();
        this.titulo = sugestao.getTitulo();
        this.descricao = sugestao.getDescricao();
        this.dataEnvio = sugestao.getDataEnvio();
        this.dataAtualizacao = sugestao.getDataAtualizacao();
        this.comentarios = sugestao.getComentarios();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public int compareTo(SugestaoResponseDTO o) {
        return getDataAtualizacao().compareTo(o.getDataAtualizacao());
    }
}
