package com.example.atomatedtest2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.atomatedtest2.entities.Pessoa;
import com.example.atomatedtest2.repositories.PessoaRespository;


@DataJpaTest
@DisplayName("PessoaRepositoryTest.class")
class PessoaRepositoryTest {

	Pessoa pessoa, pessoa2;
	

	Pessoa pessoaSalva;

	Pessoa pessoaSalva2;
	
	@Autowired
	private PessoaRespository pessoaRespository;
	
	@BeforeEach
	void setup() {
		pessoa = Pessoa.builder()
				.name("vicente Simao")
				.email("vicente@gmail.com")
				.build();
		
		pessoa2 = Pessoa.builder()
				.name("Luisa Anibal")
				.email("luisa@gmail.com")
				.build();
		

		pessoaSalva = pessoaRespository.save(pessoa);

		pessoaSalva2 = pessoaRespository.save(pessoa2);
	}
	
	@Test
	@DisplayName("It should save and return a Pessoa.")
	void It_should_save_and_return_pessoa() {
			
		assertNotNull(pessoaSalva);
	}
	
	@Test
	@DisplayName("It should update and return a Pessoa.")
	void It_should_update_and_return_pessoa() {
			
		pessoaSalva.setEmail("madalena@gmail.com");
		pessoaSalva.setName("Madalena");
		Pessoa pessoaUpdated = pessoaRespository.save(pessoaSalva);
		
		String nameExpected2 = "Madalena";
		String emailExpected2 = "madalena@gmail.com";
		
		assertEquals(pessoaUpdated.getName(), nameExpected2);
		assertEquals(pessoaUpdated.getEmail(), emailExpected2);
	}
	
	
	@Test
	@DisplayName("It should find all pessoas.")
	void it_should_find_all_pessoa() {

		List<Pessoa> pessoas = pessoaRespository.findAll();
		int expected = 2;
		
		assertEquals(pessoas.size(), expected);
	}
	
	@Test
	@DisplayName("It should find a pessoa by ID.")
	void it_should_find_pessoa_by_id() {
		
		Optional<Pessoa> pessoa = pessoaRespository.findById(1L);
		assertNotNull(pessoa);
	}
	
	@Test
	@DisplayName("It should find a pessoa with name and email.")
	void it_should_find_a_pessoa_with_name_and_email() {

		Pessoa pessoaB = pessoaRespository.findByJPQL(pessoaSalva.getName(), pessoaSalva.getEmail());
		assertNotNull(pessoaB);
	}
	
	@DisplayName("It should delete pessoa by id")
	void it_should_delete_pessoa_by_id() {
		
		pessoaRespository.delete(pessoaSalva);
		
		Pessoa pessoaOption = pessoaRespository.findById(pessoaSalva.getId()).get();
		
		assertNotNull(pessoaOption);
		
	}
	
	
	@Test
	@DisplayName("It should find by email.")
	void it_should_find_by_email() {

		Optional<Pessoa> pessoaSalva = pessoaRespository.findByEmail("vicente@gmail.com");
		assertNotNull(pessoaSalva);
	}
	

	

}
