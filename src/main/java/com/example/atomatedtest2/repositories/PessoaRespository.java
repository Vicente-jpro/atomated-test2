package com.example.atomatedtest2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.atomatedtest2.entities.Pessoa;

@Repository
public interface PessoaRespository extends JpaRepository<Pessoa, Long>{

	Optional<Pessoa> findByEmail(String email);
}
