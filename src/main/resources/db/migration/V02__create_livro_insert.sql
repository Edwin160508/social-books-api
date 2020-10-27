CREATE TABLE livro (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    data_publicacao  DATE NOT NULL,
    editora VARCHAR(50) NOT NULL,    
    autor_id BIGINT NOT NULL,
    resumo TEXT(1500),

    FOREIGN KEY (autor_id) REFERENCES autor(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO livro (nome,data_publicacao,editora, autor_id) VALUES ('Tudo sobre GIT',now(),'Casa Grande',1);