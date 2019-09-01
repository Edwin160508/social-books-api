package com.example.socialbooks.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialbooks.domain.Livro;
import com.example.socialbooks.services.LivroService;

@RestController
@RequestMapping("/api/livros")
public class LivroResources {
	
	@Autowired
	private LivroService livroService;

	@GetMapping
	public ResponseEntity<List<Livro>> listar() {		
		return ResponseEntity.ok().body(livroService.listar());
	}
}
