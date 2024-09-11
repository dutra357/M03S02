package com.exercicio.M03S02.repository;

import com.exercicio.M03S02.entities.Sugestao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SugestaoRepo extends JpaRepository<Sugestao, Long> {
}
