package com.example.atomatedtest2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.event.annotation.AfterTestMethod;

import com.example.atomatedtest2.entities.Pessoa;
import com.example.atomatedtest2.repositories.PessoaRepository;


@DataJpaTest
@DisplayName("PessoaRepositoryTest.class")
class PessoaRepositoryTest {

	Pessoa pessoa, pessoa2;
	

	Pessoa pessoaSalva;

	Pessoa pessoaSalva2;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
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
	}
	
	@Test
	@DisplayName("It should save a person.")
	void it_should_save_a_person() {
		
		pessoaSalva = this.pessoaRepository.save(pessoa);
		assertNotNull(pessoaSalva);
	}
	
	@Test
	@DisplayName("It should find a person by id.")
	void it_should_find_a_person_by_id() {
		pessoaSalva = this.pessoaRepository.save(pessoa);
		boolean pessoaExiste = this.pessoaRepository.findById(this.pessoaSalva.getId()).isPresent();
		assertTrue(pessoaExiste);
	}
	
	@Test
	@DisplayName("It should update a person.")
	void it_should_update_a_person() {
		pessoaSalva = this.pessoaRepository.save(pessoa);
		pessoaSalva.setEmail("antonia@gmail.com");
		Pessoa pessoaUpdated = this.pessoaRepository.save(pessoaSalva);
		
		String expected = "antonia@gmail.com";
		assertEquals(expected, pessoaUpdated.getEmail());
	}
	
	@Test
	@DisplayName("It should delete a person.")
	void it_should_detete_a_person() {
		pessoaSalva = this.pessoaRepository.save(pessoa);
		this.pessoaRepository.delete(pessoa);
		
		boolean pessoaExiste = this.pessoaRepository.findById(pessoaSalva.getId()).isPresent();
		assertFalse(pessoaExiste);
	}
	
	@Test
	@DisplayName("It should find all person.")
	void it_should_find_all_person() {
		pessoaSalva = this.pessoaRepository.save(pessoa);
		List<Pessoa> pessoas = this.pessoaRepository.findAll();
		
		assertTrue(!pessoas.isEmpty());
	}
	
	

	
}
