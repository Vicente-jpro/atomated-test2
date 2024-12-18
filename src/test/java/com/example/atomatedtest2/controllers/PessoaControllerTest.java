package com.example.atomatedtest2.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.atomatedtest2.dto.PessoaDTO;
import com.example.atomatedtest2.entities.Pessoa;
import com.example.atomatedtest2.service.PessoaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
@DisplayName("PessoaControllerTest.class")
public class PessoaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ModelMapper modelMapper;
	
	@MockBean
	private PessoaService pessoaService;
	
	private Pessoa pessoa, pessoaSalva;
	private PessoaDTO pessoaDTO, pessoaSalvaDTO;
	
	
	@BeforeEach
	void setup() {
		pessoa = Pessoa.builder()
				.name("vicente Simao")
				.email("vicente@gmail.com")
				.build();
		pessoaSalva = Pessoa.builder()
				.id(1L)
				.name("vicente Simao")
				.email("vicente@gmail.com")
				.build();
		
		pessoaDTO = PessoaDTO.builder()
				.id(1L)
				.name("vicente Simao")
				.email("vicente@gmail.com")
				.build();
		
		pessoaSalvaDTO = PessoaDTO.builder()
				.id(1L)
				.name("vicente Simao")
				.email("vicente@gmail.com")
				.build();
		
		
	}
	

}
