USE blog-pessoas;

CREATE TABLE IF NOT EXISTS tb_pessoas (
    id_pessoa BINARY(16) NOT NULL UNIQUE,
    nome VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    documento VARCHAR(20) UNIQUE,
    senha VARCHAR(100),
    PRIMARY KEY (id_pessoa)
);

INSERT INTO tb_pessoas (id_pessoa, nome, email, documento, senha)
VALUES
    (UUID_TO_BIN(UUID()), 'Fulano', 'fulano@email.com', '41584719079', '123abc'),
    (UUID_TO_BIN(UUID()), 'Sicrano', 'sicrano@email.com', '33794861086', 'abc123'),
    (UUID_TO_BIN(UUID()), 'Beltrano', 'beltrano@email.com', '10773370005', '321cba');

