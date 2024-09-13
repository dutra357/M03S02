package com.exercicio.M03S02.repository;

import com.exercicio.M03S02.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepo extends JpaRepository<Comentario, Long> {
}
