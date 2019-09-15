package com.app.socialbooks.service.exeption;

/**
 * 
 * Classe responsável por levantar exceção em tempo de execução referente ao camada de serviço AutorService
 * Excessão do tipo não checada
 * @author edwin
 *
 */
public class AutorExistenteException extends RuntimeException{
	
	
	private static final long serialVersionUID = 2763705908692303222L;

	/**
	 * 
	 * Construtor que pega apenas mensagem
	 * 
	 * @param mensagem
	 */
	public AutorExistenteException(String mensagem) {
		super(mensagem);
	}
	
	/**
	 * 
	 * Construtor que pega mensagem e causa
	 * 
	 * @param mensagem
	 * @param causa
	 */
	public AutorExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
	

}
