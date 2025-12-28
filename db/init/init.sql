USE blog-pessoas;

CREATE TABLE IF NOT EXISTS TB_PESSOAS (
    id_pessoa CHAR(36) PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100),
    documento VARCHAR(20),
    senha VARCHAR(100)
);

INSERT INTO TB_PESSOAS (id_pessoa, nome, email, documento, senha)
VALUES
    (UUID(), 'Fulano', 'fulano@email.com', '41584719079', '123abc'),
    (UUID(), 'Sicrano', 'sicrano@email.com', '33794861086', 'abc123'),
    (UUID(), 'Beltrano', 'beltrano@email.com', '10773370005', '321cba');
