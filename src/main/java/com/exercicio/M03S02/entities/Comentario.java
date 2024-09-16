package com.exercicio.M03S02.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_comentario")
public class Comentario implements Comparable<Comentario>, Serializable {


    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "descricao", length = 250, nullable = false)
    private String textoComentario;

    @Column(name = "data_envio")
    private LocalDateTime dataEnvio;

    @ManyToOne
    @JsonIgnore
    private Sugestao sugestao;




    public Comentario() {
    }
    public Comentario(Sugestao sugestao, String textoComentario) {
        this.sugestao = sugestao;
        this.textoComentario = textoComentario;
        this.dataEnvio = LocalDateTime.now();
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

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
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
