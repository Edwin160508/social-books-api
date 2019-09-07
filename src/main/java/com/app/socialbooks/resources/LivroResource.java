package com.app.socialbooks.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.socialbooks.domains.Livro;
import com.app.socialbooks.events.RecursoCriadoEvent;
import com.app.socialbooks.services.LivroService;
import com.app.socialbooks.services.exeption.LivroNaoEncontradoException;

@RestController
@RequestMapping("/api/livros")
public class LivroResource {
	
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
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(livroSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	} 
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id ) {	
		Optional<Livro> livroEncontrado = null;
		try {
			livroEncontrado = livroService.buscarPorId(id); 
		}catch(LivroNaoEncontradoException lne) {			
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(livroEncontrado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		try {
			livroService.remover(id);
		}catch(LivroNaoEncontradoException lne) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Livro> atualizar(@Valid @RequestBody Livro livro, @PathVariable Long id){
		livro.setId(id);
		try {			
			livroService.atualizar(livro);
		} catch (LivroNaoEncontradoException lne) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
