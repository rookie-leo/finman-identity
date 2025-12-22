USE blog-pessoas;

INSERT INTO TB_PESSOAS (id_pessoa, nome, email, documento, senha)
VALUES
    (UUID(), 'Fulano', 'fulano@email.com', '41584719079', '123abc'),
    (UUID(), 'Sicrano', 'sicrano@email.com', '33794861086', 'abc123'),
    (UUID(), 'Beltrano', 'beltrano@email.com', '10773370005', '321cba');
