package com.rookie_leo.pessoa.adapters.output.database.repositories

import com.rookie_leo.pessoa.adapters.output.database.repositories.entities.DadosUsuarioEntity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest
import kotlin.test.assertEquals

/**
 * Não testamos métodos de JpaRepository porque eles já são implementados e testados pelo Spring Data.
 * Devemos testar apenas consultas customizadas ou comportamento que contenha lógica própria da aplicação.
 * No caso aqui, é apenas para exemplo e estudos
 * */
@DataJpaTest
class PessoaRepositoryTest {

    @Autowired
    private lateinit var repository: PessoaRepository

    @Test
    fun testShouldSavePessoaAndReturnSavedPessoa() {
        val dadosUsuarioEntity = DadosUsuarioEntity(
            nome = "Nome",
            email = "email@email.com",
            documento = "12345678996",
            senha = "654789"
        )

        val result = repository.save(dadosUsuarioEntity)

        assertNotNull(result.idPessoa)
        assertEquals(dadosUsuarioEntity.nome, result.nome)
        assertEquals(dadosUsuarioEntity.email, result.email)
        assertEquals(dadosUsuarioEntity.documento, result.documento)
        assertEquals(dadosUsuarioEntity.senha, result.senha)
    }

}