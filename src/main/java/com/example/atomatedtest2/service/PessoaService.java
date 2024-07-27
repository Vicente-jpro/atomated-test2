package com.example.atomatedtest2.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.atomatedtest2.entities.Pessoa;
import com.example.atomatedtest2.exceptions.PessoaException;
import com.example.atomatedtest2.repositories.PessoaRespository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PessoaService {

	private final PessoaRespository pessoaRespository;
	
	public Pessoa salvar(Pessoa pessoa) {
		log.info("Saving the pessoa...");
		
		try {
			return pessoaRespository.save(pessoa);
		} catch (Exception e) {

			log.error("Pessoa already registered.");
			throw new PessoaException("Pessoa already registered.");
		}
		
	}
	
	public Pessoa update(Pessoa pessoa, Long idPessoa) {
		log.info("Updating the pessoa...");		
		
		Pessoa pessoaSalvo = getPessoaById(idPessoa);
		pessoa.setId(pessoaSalvo.getId());
		
		return this.salvar(pessoa);
	}
	
	public List<Pessoa> getPessoas(){
		log.info("Listing all Pessoas...");	
		return this.pessoaRespository.findAll();
	}
	
	public Pessoa getPessoaById(Long idPessoa) {
		log.info("Getting pessoa with ID: {}", idPessoa);
		
		try {
			return pessoaRespository.findById(idPessoa).get();
		} catch (NoSuchElementException e) {

			log.error("Pessoa not found ID: {}", idPessoa);
			throw new PessoaException("Pessoa not found.");
		}
		
	}
	
	public void delete(Long idPessoa) {
		log.info("Deleting pessoa with ID: {}", idPessoa);
		
		Pessoa pessoa = getPessoaById(idPessoa);
		pessoaRespository.delete(pessoa);
	}
	
	
}
