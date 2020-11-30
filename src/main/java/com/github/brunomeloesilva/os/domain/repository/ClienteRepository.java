package com.github.brunomeloesilva.os.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.brunomeloesilva.os.domain.model.ClienteModel;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long>{
	
	ClienteModel findByEmail(String email);
}
