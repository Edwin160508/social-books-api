package com.app.socialbooks.resource;

import java.net.URI;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.socialbooks.domain.Comentario;
import com.app.socialbooks.domain.Livro;
import com.app.socialbooks.event.RecursoCriadoEvent;
import com.app.socialbooks.service.LivroService;

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
		Optional<Livro> livroEncontrado = livroService.buscarPorId(id); 
		return ResponseEntity.ok().body(livroEncontrado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		livroService.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Livro> atualizar(@Valid @RequestBody Livro livro, @PathVariable Long id){
		livro.setId(id);
		livroService.atualizar(livro);
		return ResponseEntity.noContent().build();
	}
	/**
	 * 
	 * EndPoint responsável por cadastrar um comentário referente ao livro
	 * através do Id, 
	 * 
	 * @param livroId
	 * @param comentario
	 */
	@PostMapping("/{id}/comentarios")
	public ResponseEntity<Void> adicionarComentario(@PathVariable Long id, 
			@Valid @RequestBody Comentario comentario) {
		livroService.cadastrarComentario(id, comentario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{livroId}/comentarios")
	public ResponseEntity<List<Comentario>> listarComentariosLivro(@PathVariable Long livroId){
		List<Comentario> listaComentarios = livroService.listarTodosComentariosLivro(livroId);
		return ResponseEntity.ok().body(listaComentarios);
	}
}
