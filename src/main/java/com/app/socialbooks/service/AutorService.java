package com.app.socialbooks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.socialbooks.domain.Autor;
import com.app.socialbooks.repository.AutorRepository;
import com.app.socialbooks.service.exeption.AutorExistenteException;
import com.app.socialbooks.service.exeption.AutorNaoEncontradoException;

@Service
public class AutorService {
	
	@Autowired
	private AutorRepository autorRepository;
	
	public List<Autor> listar(){
		return autorRepository.findAll();
	}
	/**
	 * 
	 * Método responsável por fazer 2 verificações 
	 * 1- Verificar se existe autor na base de dados caso negativo 
	 * 	  lança exceção AutorNaoEncontradoException.
	 * 
	 * 2- Verificar se autor selecionado já existe na base caspo positivo
	 *    lança exceção AutorExistenteException.
	 * 
	 * @param id
	 * @return Optional<Autor>
	 */
	public Optional<Autor> buscarPorId(Long id) {
		Optional<Autor> autorEncontrado = autorRepository.findById(id);
		
		if(!autorEncontrado.isPresent())
			throw new AutorNaoEncontradoException("Autor não encontrado na base de dados.");
		
		if(autorEncontrado.isPresent()) 
			throw new AutorExistenteException("Esse Autor já existe na base de dados.");
		
		return autorEncontrado;
	}
	
	/**
	 * 
	 * Método responsável por fazer 1 verificação 
	 * 1- Verificar se existe autor na base de dados caso negativo 
	 * 	  lança exceção AutorNaoEncontradoException.
	 * 
	 * @param id
	 * @return Optional<Autor>
	 */
	public Optional<Autor> buscarAutorPorId(Long id) {
		Optional<Autor> autorEncontrado = autorRepository.findById(id);
		
		if(!autorEncontrado.isPresent())
			throw new AutorNaoEncontradoException("Autor não encontrado na base de dados.");				
		
		return autorEncontrado;
	}
	
	/**
	 *  
	 *  Método responsável por cadastrar um Autor caso ele não exista na base de dados.
	 *  
	 * @param autor
	 * @return Autor
	 */
	public Autor cadastrar(Autor autor) {
		if(autor.getId() != null) {
			buscarPorId(autor.getId()).get();
		}
		
		return autorRepository.save(autor);
	}
}
