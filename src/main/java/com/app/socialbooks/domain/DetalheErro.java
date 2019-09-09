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
	
	private String title;
	
	private Long status;
	
	private Long timeStamp;
	
	private String messageDesenvolved;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessageDesenvolved() {
		return messageDesenvolved;
	}

	public void setMessageDesenvolved(String messageDesenvolved) {
		this.messageDesenvolved = messageDesenvolved;
	}
	
	
}
