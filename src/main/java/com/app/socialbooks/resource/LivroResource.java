package com.app.socialbooks.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	/**
	 * 
	 * EndPoint responsável por listar todos os Livros.
	 * 
	 * @return List<Livro>
	 */
	@GetMapping
	public ResponseEntity<List<Livro>> listar() {		
		return ResponseEntity.ok().body(livroService.listar());
	}
	
	/**
	 * 
	 * EndPoint responsável por cadastrar um determinado livro
	 * retornando ao front-end o location do livro recem criado.
	 * 
	 * @param livro
	 * @param response
	 * @return Livro
	 */
	@PostMapping
	public ResponseEntity<Livro> cadastrar(@Valid @RequestBody Livro livro, HttpServletResponse response){
		Livro livroSalvo = livroService.cadastrar(livro);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, livroSalvo.getId()));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(livroSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	} 
	
	/**
	 * 
	 * EndPoint responsável por localizar um determinado livro
	 * Atravéz do parâmetro id
	 * 
	 * @param id
	 * @return Livro
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Livro> buscarPorId(@PathVariable Long id ) {	
		Optional<Livro> livroEncontrado = livroService.buscarPorId(id);
		
		CacheControl cacheControl = CacheControl.maxAge(50, TimeUnit.SECONDS); 
		return ResponseEntity.ok().cacheControl(cacheControl).body(livroEncontrado.get());
	}
	
	/**
	 * 
	 * EndPoint responsável por remover um livro atravéz
	 * do parâmetro id.
	 * 
	 * @param id
	 * @void
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		livroService.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * 
	 * EndPoint responsável por atualizar um determinado livro
	 * atravéz do parâmetro id.
	 * 
	 * @param livro
	 * @param id
	 * @return Livro
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Livro> atualizar(@Valid @RequestBody Livro livro, @PathVariable Long id){
		livro.setId(id);
		livroService.atualizar(livro);
		return ResponseEntity.noContent().build();
	}
	/**
	 * 
	 * EndPoint responsável por cadastrar um comentário referente ao livro
	 * através do livroId, 
	 * 
	 * @param livroId
	 * @param comentario
	 */
	@PostMapping("/{livroId}/comentarios")
	public ResponseEntity<Void> adicionarComentario(@PathVariable Long livroId, 
			@Valid @RequestBody Comentario comentario) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		comentario.setUsuario(auth.getName());
		
		livroService.cadastrarComentario(livroId, comentario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	
	/**
	 * 
	 * EndPoint responsável por listar todos os comentários referente ao livro
	 * específico passando livroId como parâmetro.
	 * 
	 * @param livroId
	 * @return List<Comentario>
	 */
	@GetMapping("/{livroId}/comentarios")
	public ResponseEntity<List<Comentario>> listarComentariosLivro(@PathVariable Long livroId){
		List<Comentario> listaComentarios = livroService.listarTodosComentariosLivro(livroId);
		return ResponseEntity.ok().body(listaComentarios);
	}
}
