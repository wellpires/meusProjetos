-- Table: tbl_livros

-- DROP TABLE tbl_livros;

CREATE TABLE tbl_livros
(
  id_livro serial NOT NULL,
  titulo character varying(255),
  descricao text,
  preco numeric(10,2)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tbl_livros
  OWNER TO postgres;


INSERT INTO tbl_livros(titulo, descricao, preco) VALUES ('LIVRO 01', 'DESC 01', 2.0);
INSERT INTO tbl_livros(titulo, descricao, preco) VALUES ('LIVRO 02', 'DESC 02', 2.6);
INSERT INTO tbl_livros(titulo, descricao, preco) VALUES ('LIVRO 03', 'DESC 03', 2.8);
INSERT INTO tbl_livros(titulo, descricao, preco) VALUES ('LIVRO 04', 'DESC 04', 4.6);
INSERT INTO tbl_livros(titulo, descricao, preco) VALUES ('LIVRO 05', 'DESC 05', 3.9);
INSERT INTO tbl_livros(titulo, descricao, preco) VALUES ('LIVRO 06', 'DESC 06', 9.6);
INSERT INTO tbl_livros(titulo, descricao, preco) VALUES ('LIVRO 07', 'DESC 07', 4.7);
INSERT INTO tbl_livros(titulo, descricao, preco) VALUES ('LIVRO 08', 'DESC 08', 1.6);
INSERT INTO tbl_livros(titulo, descricao, preco) VALUES ('LIVRO 09', 'DESC 09', 6.4);
INSERT INTO tbl_livros(titulo, descricao, preco) VALUES ('LIVRO 10', 'DESC 10', 4.6);