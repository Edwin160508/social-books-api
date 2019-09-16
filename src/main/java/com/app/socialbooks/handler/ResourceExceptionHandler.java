package com.app.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.socialbooks.domain.DetalheErro;
import com.app.socialbooks.service.exeption.AutorExistenteException;
import com.app.socialbooks.service.exeption.AutorNaoEncontradoException;
import com.app.socialbooks.service.exeption.LivroNaoEncontradoException;

/**
 * 
 * Classe Responsável por manipular excessões que ocorrer em todos os 
 * Resources existentes do projeto.
 * 
 * @author edwin
 *
 */

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(LivroNaoEncontradoException.class)
	public ResponseEntity<DetalheErro> handlerLivroNaoEncontradoException
							(LivroNaoEncontradoException e, HttpServletRequest request){
		
			DetalheErro detalheErro = new DetalheErro();
			detalheErro.setStatus(404l);
			detalheErro.setTitulo("Livro não encontrado");
			detalheErro.setMenssagemDesenvolvedor("https://erros.socialbooks.com/404");
			detalheErro.setDataHora(System.currentTimeMillis());
			
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalheErro);
	}
	
	/**
	 * 
	 * Método responsável por tratar erro de negocio caso na tentativa
	 * de cadastrar Autor que já existe na base de dados.
	 * 
	 * @param e
	 * @param request
	 * @return ResponseEntity
	 */
	
	@ExceptionHandler(AutorExistenteException.class)
	public ResponseEntity<DetalheErro> handlerAutorExistenteException
							(AutorExistenteException e, HttpServletRequest request){
		
			DetalheErro detalheErro = new DetalheErro();
			detalheErro.setStatus(409l);
			detalheErro.setTitulo("Esse autor já existe na base de dados.");
			detalheErro.setMenssagemDesenvolvedor("https://erros.socialbooks.com/409");
			detalheErro.setDataHora(System.currentTimeMillis());
			
		return ResponseEntity.status(HttpStatus.CONFLICT).body(detalheErro);
	}
	
	/**
	 * 
	 * Método responsável por tratar erro caso não encontre Autor na base de dados.
	 * 
	 * @param e
	 * @param request
	 * @return ResponseEntity
	 */
	@ExceptionHandler(AutorNaoEncontradoException.class)
	public ResponseEntity<DetalheErro> handlerAutorNaoEncontradoException
							(AutorNaoEncontradoException e, HttpServletRequest request){
		
			DetalheErro detalheErro = new DetalheErro();
			detalheErro.setStatus(404l);
			detalheErro.setTitulo("Autor não encontrado na base de dados.");
			detalheErro.setMenssagemDesenvolvedor("https://erros.socialbooks.com/404");
			detalheErro.setDataHora(System.currentTimeMillis());
			
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalheErro);
	}
	
	/**
	 * 
	 * Método responsável por tratar erro de integridade de base de dados.
	 * 
	 * @param e
	 * @param request
	 * @return ResponseEntity
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalheErro> handlerDataIntegrityViolationException
							(DataIntegrityViolationException e, HttpServletRequest request){
		
			DetalheErro detalheErro = new DetalheErro();
			detalheErro.setStatus(400l);
			detalheErro.setTitulo("Requisição inválida.");
			detalheErro.setMenssagemDesenvolvedor("https://erros.socialbooks.com/400");
			detalheErro.setDataHora(System.currentTimeMillis());
			
		return ResponseEntity.badRequest().body(detalheErro);
	}
}
