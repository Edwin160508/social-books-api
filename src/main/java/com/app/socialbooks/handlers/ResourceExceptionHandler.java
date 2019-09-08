package com.app.socialbooks.handlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.socialbooks.services.exeption.LivroNaoEncontradoException;

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
	public ResponseEntity<Void> handlerLivroNaoEncontradoException
							(LivroNaoEncontradoException e, HttpServletRequest request){
		return ResponseEntity.notFound().build();
	}
	
}
