package com.github.brunomeloesilva.os.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.brunomeloesilva.os.domain.model.OrdemServicoModel;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServicoModel, Long>{

}
