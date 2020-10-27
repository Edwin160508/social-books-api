CREATE TABLE autor (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    data_nascimento  DATE NOT NULL,
    nacionalidade VARCHAR(50) NOT NULL    
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO autor (nome,data_nascimento,nacionalidade) VALUES ('Edwin Lima',now(),'Brasileira');