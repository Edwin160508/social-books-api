package com.app.socialbooks.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonInclude(Include.NON_NULL)
	private Long id;
	
	@NotEmpty(message = "Nome é um campo obrigatório.")
	private String nome;
	
	@NotNull(message = "Data Nascimento é um campo obrigatório.")
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@NotNull(message = "Nacionalidade é um campo obrigatório.")
	private String nacionalidade;
	
	@JsonInclude(Include.NON_NULL)	
	@OneToMany(mappedBy = "autor")
	@JsonIgnore
	private List<Livro> listaLivros;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public List<Livro> getListaLivros() {
		return listaLivros;
	}

	public void setListaLivros(List<Livro> listaLivros) {
		this.listaLivros = listaLivros;
	}
	
	
}
