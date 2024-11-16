package com.example.atomatedtest2.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.atomatedtest2.entities.Pessoa;
import com.example.atomatedtest2.exceptions.PessoaException;
import com.example.atomatedtest2.repositories.PessoaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PessoaService {

	private final PessoaRepository pessoaRespository;
	
	public Pessoa salvar(Pessoa pessoa) {
			log.info("Saving a pessoa...");

		try {
			return pessoaRespository.save(pessoa);
		} catch (Exception e) {

			log.error("Pessoa already registered.");
			throw new PessoaException("Pessoa already registered.");
		}
		
	}
	
	public Pessoa update(Pessoa pessoa, Long idPessoa) {
		log.info("Updating the pessoa with id: {} ...", idPessoa);		
		
		Pessoa pessoaSalvo = getPessoaById(idPessoa);
		pessoa.setId(pessoaSalvo.getId());
		
		return this.salvar(pessoa);
	}
	
	public Pessoa findByNameAndEmail(String name, String email) {
	log.info("Getting pessoa with name: {} and email: {}, not found",name, email);		
		try {
			return pessoaRespository.findByJPQL(name, email);
		} catch (NoSuchElementException e) {

			log.error("Pessoa with name: {} and email: {}, not found",name, email);
			throw new PessoaException("Pessoa not found.");
		}
		 
	}
	
	public List<Pessoa> getPessoas(){
		log.info("Listing all Pessoas...");	
		
		return this.pessoaRespository.findAll();
		

	}
	
	public Pessoa getPessoaById(Long idPessoa) {
		log.info("Getting pessoa with ID: {}", idPessoa);
		
		try {
			Pessoa pessoa = 
					pessoaRespository.findById(idPessoa)
									 .isPresent() ? pessoaRespository.findById(idPessoa).get(): null; 
			return pessoa;
		} catch (Exception e) {

			log.error("Pessoa not found ID: {}", idPessoa);
			throw new PessoaException("Pessoa not found.");
		}
		
	}
	
	public Pessoa getPessoaByEmail(String email) {
		log.info("Getting pessoa by email: {}", email);
		
		try {
			return pessoaRespository.findByEmail(email).get();
		} catch (NoSuchElementException | NullPointerException e) {

			log.error("Pessoa not found email: {}", email);
			throw new PessoaException("Pessoa not found.");
		}
		
	}
	
	public void delete(Long idPessoa) {
		log.info("Deleting pessoa with ID: {}", idPessoa);
		
		Pessoa pessoa = getPessoaById(idPessoa);
		pessoaRespository.delete(pessoa);
	}
	
	
}
