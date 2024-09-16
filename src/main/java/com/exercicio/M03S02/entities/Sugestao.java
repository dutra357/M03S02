package com.exercicio.M03S02.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_sugestao")
public class Sugestao implements Comparable<Sugestao>, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Column(name = "descricao", length = 250, nullable = false)
    private String descricao;

    @Column(name = "data_envio")
    private LocalDateTime dataEnvio;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @OneToMany(mappedBy = "sugestao", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comentario> comentarios;



    public Sugestao() {}
    public Sugestao(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataEnvio = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    public List<Comentario> getComentarios() {
        return comentarios;
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

    public void setDataAtualizacao() {
        this.dataAtualizacao = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sugestao sugestao = (Sugestao) o;
        return getId() == sugestao.getId() && Objects.equals(getDataAtualizacao(), sugestao.getDataAtualizacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDataAtualizacao());
    }

    @Override
    public String toString() {
        return "Sugestao{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataEnvio=" + dataEnvio +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }

    @Override
    public int compareTo(Sugestao o) {
        return getDataAtualizacao().compareTo(o.getDataAtualizacao());
    }
}
