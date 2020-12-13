package com.github.brunomeloesilva.os.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.brunomeloesilva.os.domain.model.ComentarioModel;

@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioModel, Long> {

}
