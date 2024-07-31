package com.example.atomatedtest2.controllers;




import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.atomatedtest2.dto.PessoaDTO;
import com.example.atomatedtest2.entities.Pessoa;
import com.example.atomatedtest2.service.PessoaService;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
@RequestMapping("/pessoas")
public class PessoaController {
	
	private final PessoaService pessoaService;
	private final ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PessoaDTO salvar(@RequestBody PessoaDTO pessoaDTO) {
		
		Pessoa pessoa = modelMapper.map(pessoaDTO, Pessoa.class);
		Pessoa pessoaSaved = pessoaService.salvar(pessoa);
		
		PessoaDTO pessoaSavedDTO = modelMapper.map(pessoaSaved, PessoaDTO.class);

		return pessoaSavedDTO;
	}
	
	@PatchMapping("/{id_pessoa}")
	@ResponseStatus(HttpStatus.CREATED)
	public PessoaDTO update(@RequestBody Pessoa pessoaDTO, @PathVariable("id_pessoa") Long idPessoa) {
		
		Pessoa pessoa = modelMapper.map(pessoaDTO, Pessoa.class);
		Pessoa pessoaSaved =  pessoaService.update(pessoa, idPessoa);
		
		PessoaDTO pessoaSavedDTO = modelMapper.map(pessoaSaved, PessoaDTO.class);

		return pessoaSavedDTO;
		
	}
	
	@GetMapping("/{id_pessoa}")
	@ResponseStatus(HttpStatus.OK)
	public PessoaDTO getPessoaDTOById(@PathVariable("id_pessoa") Long idPessoa) {
		
		Pessoa pessoaSaved =  pessoaService.getPessoaById(idPessoa);
		PessoaDTO pessoaDTO = modelMapper.map(pessoaSaved, PessoaDTO.class);
		
		return pessoaDTO;
	}
	
	@GetMapping("/search")
	@ResponseStatus(HttpStatus.OK)
	public PessoaDTO getPessoaByEmail(@RequestParam("email") String email) {
		
		Pessoa pessoaSaved =  pessoaService.getPessoaByEmail(email);
		PessoaDTO pessoaDTO = modelMapper.map(pessoaSaved, PessoaDTO.class);
		
		return pessoaDTO;
	}
	
	
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<PessoaDTO> getPessoas() {
		
		return pessoaService.getPessoas()
				.stream()
				.map( pessoa ->{
					PessoaDTO pessoaDTO = modelMapper.map(pessoa, PessoaDTO.class);
					
					return pessoaDTO;
		}).collect(Collectors.toList());
	}
	
	@DeleteMapping("/{id_pessoa}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable("id_pessoa") Long idPessoaDTO) {
		
		pessoaService.delete(idPessoaDTO);
	}
	
}
