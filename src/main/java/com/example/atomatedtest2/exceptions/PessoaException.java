package com.example.atomatedtest2.exceptions;

public class PessoaException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public PessoaException(String errorMessage){
		super(errorMessage);
	}

}
