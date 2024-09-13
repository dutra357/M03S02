package com.exercicio.M03S02.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_comentario")
public class Comentario implements Comparable<Comentario> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "descricao", length = 250, nullable = false)
    private String textoComentario;

    @Column(name = "data_envio", nullable = false)
    private LocalDate dataEnvio;

    @JsonIgnore()
    @ManyToOne
    private Sugestao sugestao;


    public Comentario() {
    }
    public Comentario(Sugestao sugestao, String textoComentario) {
        this.sugestao = sugestao;
        this.textoComentario = textoComentario;
        this.dataEnvio = LocalDate.now();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Sugestao getSugestaoId() {
        return sugestao;
    }

    public void setSugestaoId(Sugestao sugestaoId) {
        this.sugestao = sugestaoId;
    }

    public String getTextoComentario() {
        return textoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comentario that = (Comentario) o;
        return getId() == that.getId() && Objects.equals(getDataEnvio(), that.getDataEnvio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDataEnvio());
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", sugestaoId=" + sugestao +
                ", textoComentario='" + textoComentario + '\'' +
                ", dataEnvio=" + dataEnvio +
                '}';
    }

    @Override
    public int compareTo(Comentario o) {
        return getDataEnvio().compareTo(o.getDataEnvio());
    }
}
