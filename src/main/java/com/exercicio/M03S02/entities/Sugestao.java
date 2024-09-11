package com.exercicio.M03S02.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_sugestao")
public class Sugestao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    long id;

    @Column(name = "titulo", length = 100, nullable = false)
    String titulo;

    @Column(name = "descricao", length = 250, nullable = false)
    String descricao;

    @Column(name = "data_envio", nullable = false)
    LocalDate dataEnvio;

    @Column(name = "data_atualizacao", nullable = false)
    LocalDate dataAtualizacao;

    public Sugestao() {}
    public Sugestao(String titulo, String descricao, LocalDate dataEnvio, LocalDate dataAtualizacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataEnvio = dataEnvio;
        this.dataAtualizacao = dataAtualizacao;
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

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDate dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sugestao sugestao = (Sugestao) o;
        return getId() == sugestao.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
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
}
