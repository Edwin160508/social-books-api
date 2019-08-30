package com.example.socialbooks.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialbooks.domain.Livro;

@RestController
public class LivroResources {

	@RequestMapping(value ="/livros", method = RequestMethod.GET)
	public List<Livro> listar() {
		Livro l1 = new Livro("Rest aplicado");
		Livro l2 = new Livro("Git passso-a-passo");
		List<Livro> livros = Arrays.asList(l1, l2);
		return livros;
	}
}
