package com.example.socialbooks.resources;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id ) {	
		Optional<Livro> livroEncontrado = livroService.buscarPorId(id);
		if(!livroEncontrado.isPresent())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(livroEncontrado);
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		livroService.remover(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Livro> atualizar(@Valid @RequestBody Livro livro, @PathVariable Long id){
		livro.setId(id);
		Livro livroAtualizado = livroService.atualizar(livro, id);
		return ResponseEntity.ok(livroAtualizado);
	}
}
