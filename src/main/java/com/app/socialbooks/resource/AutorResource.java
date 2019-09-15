package com.app.socialbooks.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.socialbooks.domain.Autor;
import com.app.socialbooks.event.RecursoCriadoEvent;
import com.app.socialbooks.service.AutorService;

@RestController
@RequestMapping("/api/autores")
public class AutorResource {
	
	@Autowired
	private AutorService autorService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	/**
	 * 
	 * EndPoint responsável por listar todos os Autores.
	 * 
	 * @return List<Autor>
	 */
	@GetMapping
	public ResponseEntity<List<Autor>> listar(){
		return ResponseEntity.ok().body(autorService.listar()); 
	}

	@PostMapping
	public ResponseEntity<Autor> cadastrar(@Valid @RequestBody Autor autor, HttpServletResponse response){
		Autor autorSalvo = autorService.cadastrar(autor);
		publisher.publishEvent(new RecursoCriadoEvent(autorSalvo, response, autorSalvo.getId()));
		
		/** Criando lacation do objeto recem salvo na base de dados. **/
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(autorSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Autor> buscarPorId(@PathVariable Long id){
		Optional<Autor> autorEncontrado = autorService.buscarAutorPorId(id);
		return ResponseEntity.ok().body(autorEncontrado.get());
	}
}
