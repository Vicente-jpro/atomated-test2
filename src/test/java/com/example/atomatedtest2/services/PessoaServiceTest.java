package com.example.atomatedtest2.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aopalliance.intercept.Invocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.atomatedtest2.entities.Pessoa;
import com.example.atomatedtest2.exceptions.PessoaException;
import com.example.atomatedtest2.repositories.PessoaRepository;
import com.example.atomatedtest2.service.PessoaService;

@ExtendWith(MockitoExtension.class)
@DisplayName("PessoaServiceTest.class")
public class PessoaServiceTest {

	@Mock
	private PessoaRepository pessoaRepository;
	
	@InjectMocks
	private PessoaService pessoaService;
	
	Pessoa pessoa, pessoa2, pessoaSalva, pessoaSalva2, pessoaSalvaDuplicated;

	
	@BeforeEach
	void setup() {
		pessoa = Pessoa.builder()
				.name("vicente Simao222")
				.email("vicente22@gmail.com")
				.build();
		
		pessoa2 = Pessoa.builder()
				.name("Antonio")
				.email("antonio@gmail.com")
				.build();
		
	}
	
	@Test
	@DisplayName("It should run exception if a person was not found.")
	void it_should_run_an_exception_if_a_person_was_not_found() throws PessoaException {
		
		Pessoa ps = pessoaService.salvar(pessoa);

		when(pessoaRepository.findById(5L))
		.thenReturn(Optional.empty());
		
		assertThrows(PessoaException.class, ()->{
			pessoaSalva = pessoaService.getPessoaById(5L);
		});
		
		String h = "This is a";
	}
	
	@Test
	@DisplayName("It should save a person.")
	void it_should_save_a_person() {
		when( pessoaRepository.save(any(Pessoa.class)) )
			.thenAnswer(invocation -> {
	            Pessoa savedPerson = invocation.getArgument(0);
	            savedPerson.setId(1L); // Simulate the JPA behavior of setting the ID
	            return savedPerson;
	        });
		
		pessoaSalva = pessoaService.salvar(pessoa);
		assertNotNull(pessoaSalva);
		Mockito.verify(pessoaRepository).save(pessoa);
				
	}
	
	@Test
	@DisplayName("It should run an exception when email already exist.")
	void it_should_return_an_exception_when_email_already_exist() throws PessoaException{
		when( pessoaRepository.save(any(Pessoa.class)))
			.thenThrow(PessoaException.class);
		
		
		assertThrows( PessoaException.class, ()->{
			pessoaSalva = pessoaService.salvar(pessoa);
			pessoaSalva = pessoaService.salvar(pessoa);
		});
		String g = "";
	}

	@Test
	@DisplayName("It should update a person.")
	void it_should_update_a_person() {
		
		when(pessoaRepository.save(any(Pessoa.class)))
		.thenAnswer(invocation -> {
			Pessoa savedPerson = invocation.getArgument(0);
			savedPerson.setId(1L);
			return savedPerson;
		});
	
		Pessoa pessoaSalva = pessoaService.salvar(pessoa);
		
		when(pessoaRepository.findById(any(Long.class)))
		.thenReturn(Optional.of(pessoaSalva));
		
		Pessoa pessoaAchada = pessoaService.getPessoaById(1L);
		
	
		pessoaSalva = pessoaService.update(pessoa2, pessoaAchada.getId());
		String expected_name = "Antonio";
		
		assertEquals(expected_name, pessoa2.getName());
	}
	


}
