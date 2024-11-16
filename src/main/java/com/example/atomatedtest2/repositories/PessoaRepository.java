package com.example.atomatedtest2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.atomatedtest2.entities.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	Optional<Pessoa> findByEmail(String email);
	
	//Os atributos e o nome da tabela devem obedecer os mesmos nome que a classe Pessoa usa.
	// A pesquisa e realizada aparttir dos atributos da classe e nao pelos atributos das colunas da base de dados
	@Query("select p from Pessoa p where p.name = ?1 and p.email = ?2")
	Pessoa findByJPQL(String name, String email);
}
