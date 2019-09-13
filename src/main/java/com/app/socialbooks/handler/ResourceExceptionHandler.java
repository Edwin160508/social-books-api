package com.app.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.socialbooks.domain.DetalheErro;
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
	
}
