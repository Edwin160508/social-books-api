package com.app.socialbooks.domain;
/**
 * 
 * Classe responsável por reresentar detalhe do erro ocorrido
 * de acordo com a exceção levantada na aplicação.
 * 
 * @author edwin
 *
 */
public class DetalheErro {
	
	private String titulo;
	
	private Long status;
	
	private Long tempo;
	
	private String menssagemDesenvolvida;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getTempo() {
		return tempo;
	}

	public void setTempo(Long tempo) {
		this.tempo = tempo;
	}

	public String getMenssagemDesenvolvida() {
		return menssagemDesenvolvida;
	}

	public void setMenssagemDesenvolvida(String menssagemDesenvolvida) {
		this.menssagemDesenvolvida = menssagemDesenvolvida;
	}
	
	
}
