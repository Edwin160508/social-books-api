CREATE TABLE comentario (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    texto TEXT(1500) NOT NULL,
    data_comentario  DATE NOT NULL,
    usuario VARCHAR(50),
    livro_id BIGINT NOT NULL,

    FOREIGN KEY (livro_id) REFERENCES autor(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO comentario (texto,data_comentario,usuario, livro_id) VALUES ('Livro muito bom',now(),'João Neto',1);