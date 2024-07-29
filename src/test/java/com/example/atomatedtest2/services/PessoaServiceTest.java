package com.example.atomatedtest2.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.atomatedtest2.entities.Pessoa;
import com.example.atomatedtest2.exceptions.PessoaException;
import com.example.atomatedtest2.repositories.PessoaRespository;
import com.example.atomatedtest2.service.PessoaService;

@ExtendWith(MockitoExtension.class)
@DisplayName("PessoaServiceTest.class")
public class PessoaServiceTest {

	@Mock
	private PessoaRespository pessoaRepository;
	
	@InjectMocks
	private PessoaService pessoaService;
	
	Pessoa pessoa, pessoa2, pessoaSalva, pessoaSalva2, pessoaSalvaDuplicated;

	
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
		
		pessoaSalva = Pessoa.builder()
				.id(1L)
				.name("vicente Simao")
				.email("vicente@gmail.com")
				.build();
		
		pessoaSalvaDuplicated = Pessoa.builder()
				.id(2L)
				.name("vicente Simao")
				.email("vicente@gmail.com")
				.build();
		
		pessoaSalva2 = Pessoa.builder()
				.id(2L)
				.name("Luisa Anibal")
				.email("luisa@gmail.com")
				.build();

	}
	
	@Test
	@DisplayName("It should save a Pessoa")
	void it_should_save_a_pessoa() {
		
		when( pessoaRepository.save(pessoa))
			.thenReturn(pessoaSalva);
		
		Pessoa pessoaS = pessoaService.salvar(pessoa);
		
		assertNotNull(pessoaS);
		verify(pessoaRepository, times(1)).save(pessoa);
	}
	
	@Test
	@DisplayName("It should return an exception when email already exist Pessoa")
	void it_should_return_an_error_message_when_email_already_exist() throws PessoaException{
		
		when( pessoaRepository.save(pessoa))
			.thenThrow(PessoaException.class);

		assertThrows(PessoaException.class, ()->{
			pessoaService.salvar(pessoa);
		});

	}
	
	@Test
	@DisplayName("It should update a pessoa")
	void it_should_update_and_return_pessoa() {
		
		when(pessoaRepository.findById(1L))
			.thenReturn(Optional.of(pessoaSalva));
			
		when( pessoaRepository.save(pessoa))
			.thenReturn(pessoaSalva);
		
		Pessoa pessoaS = pessoaService.getPessoaById(pessoaSalva.getId());
		
		pessoaS.setEmail("aaaaaaa@gmail.com");
		pessoaS.setName("AAAAAAAAAAA");
		
		Pessoa pessoaAtualizada = pessoaService.update(pessoa, 1L);
		
		assertNotNull(pessoaS);
		verify(pessoaRepository, times(1)).save(pessoa);
	}
	
	
	
	@Test
	@DisplayName("It should find a list of Pessoas")
	void it_should_find_a_list_of_person() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		
		pessoas.add(pessoaSalva);
		pessoas.add(pessoaSalva2);
		
		when(pessoaRepository.findAll())
			.thenReturn(pessoas);
		
		List<Pessoa> listaPessoas = pessoaService.getPessoas();
		assertTrue( listaPessoas.size() > 0);
		
	}
	
	@Test
	@DisplayName("It should find pessoa by id.")
	void it_should_find_pessoa_by_id() {
		when(pessoaRepository.findById(1L))
			.thenReturn(Optional.of(pessoaSalva));
		
		Pessoa pessoa = pessoaService.getPessoaById(1L);
		assertNotNull(pessoa);
	}
	
	@Test
	@DisplayName("It should throw PersonException when pessoa was not found.")
	void it_should_run_an_exception_when_pessoa_was_not_found() {
		when(pessoaRepository.findById(4L))
			.thenThrow(PessoaException.class);
		
		when(pessoaRepository.save(pessoa))
			.thenReturn(pessoaSalva);
		Pessoa ps = pessoaService.salvar(pessoa);

		
		
		assertThrows(PessoaException.class, ()->{
			 pessoaService.getPessoaById(4L);
			
		});
		
	}
	
	
	
	
	

}
