package com.example.socialbooks.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialbooks.domain.Livro;
import com.example.socialbooks.event.RecursoCriadoEvent;
import com.example.socialbooks.services.LivroService;

@RestController
@RequestMapping("/api/livros")
public class LivroResources {
	
	@Autowired
	private LivroService livroService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public ResponseEntity<List<Livro>> listar() {		
		return ResponseEntity.ok().body(livroService.listar());
	}
	
	@PostMapping
	public ResponseEntity<Livro> cadastrar(@Valid @RequestBody Livro livro, HttpServletResponse response){
		Livro livroSalvo = livroService.cadastrar(livro);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, livroSalvo.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(livroSalvo);
		
	} 
	
}
