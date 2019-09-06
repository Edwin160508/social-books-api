package com.app.socialbooks.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.socialbooks.domain.Livro;
import com.app.socialbooks.repository.LivroRepository;



@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	

	public Livro cadastrar(Livro livro) {
		return livroRepository.save(livro);
	}
	
	public List<Livro> listar(){
		return livroRepository.findAll();
	}
	
	public Optional<Livro> buscarPorId(Long id) {
		return livroRepository.findById(id);
	}
	
	public void remover(Long id) {
		livroRepository.deleteById(id);
	}
	
	public Livro atualizar(Livro livro, Long id) {
		return livroRepository.save(livro);
	}
}
