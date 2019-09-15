package com.app.socialbooks.service.exeption;

/**
 * 
 * Classe responsável por levantar exceção em tempo de execução referente ao camada de serviço LivroService
 * Excessão do tipo não checada
 * @author edwin
 *
 */
public class AutorNaoEncontradoException extends RuntimeException{
	
	
	private static final long serialVersionUID = -8801395388477801069L;

	/**
	 * 
	 * Construtor que pega apenas mensagem
	 * 
	 * @param mensagem
	 */
	public AutorNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	/**
	 * 
	 * Construtor que pega mensagem e causa
	 * 
	 * @param mensagem
	 * @param causa
	 */
	public AutorNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
	

}
