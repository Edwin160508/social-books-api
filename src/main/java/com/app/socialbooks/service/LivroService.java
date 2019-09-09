package com.app.socialbooks.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.app.socialbooks.domain.Livro;
import com.app.socialbooks.repository.LivroRepository;
import com.app.socialbooks.service.exeption.LivroNaoEncontradoException;



@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroRepository;
	

	public Livro cadastrar(Livro livro) {
		livro.setId(null);
		return livroRepository.save(livro);
	}
	
	public List<Livro> listar(){
		return livroRepository.findAll();
	}
	
	public Optional<Livro> buscarPorId(Long id) {
		Optional<Livro> livroEncontrado = livroRepository.findById(id);
		if(!livroEncontrado.isPresent()) {
			throw new LivroNaoEncontradoException("O livro não foi encontrado.");
		}
		return livroEncontrado;
	}
	
	public void remover(Long id) {
		try {
			livroRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("O livro não foi encontrado.");
		}
	}
	
	public void atualizar(Livro livro) {
		verifcarExistencia(livro);
		livroRepository.save(livro);
	}
	
	private void verifcarExistencia(Livro livro) {
		buscarPorId(livro.getId());
	}
}
