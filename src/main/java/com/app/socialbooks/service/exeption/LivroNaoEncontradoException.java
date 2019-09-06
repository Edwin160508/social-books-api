package com.app.socialbooks.service.exeption;

/**
 * 
 * Classe responsável por levantar exceção em tempo de execução referente ao camada de serviço LivroService
 * Excessão do tipo não checada
 * @author edwin
 *
 */
public class LivroNaoEncontradoException extends RuntimeException{
	
	
	private static final long serialVersionUID = 2472418024193432954L;

	/**
	 * 
	 * Construtor que pega apenas mensagem
	 * 
	 * @param mensagem
	 */
	public LivroNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	/**
	 * 
	 * Construtor que pega mensagem e causa
	 * 
	 * @param mensagem
	 * @param causa
	 */
	public LivroNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
	

}
