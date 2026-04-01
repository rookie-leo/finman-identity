package com.rookie_leo.pessoa.adapters.output.database.impl

import com.rookie_leo.pessoa.adapters.output.database.repositories.PessoaRepository
import com.rookie_leo.pessoa.adapters.output.database.repositories.entities.DadosUsuarioEntity
import com.rookie_leo.pessoa.adapters.output.database.exceptions.DatabaseException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class DataBaseAccessImplTest {

    @Mock
    private lateinit var repository: PessoaRepository

    @InjectMocks
    private lateinit var dataBase: DataBaseAccessImpl

    @Test
    fun `deve salvar um usuario com sucesso no banco de dados`() {
        val entity = DadosUsuarioEntity(
            nome = "Nome",
            email = "email@email.com",
            documento = "12345678996",
            senha = "654789"
        )

        val savedEntity = entity.copy()

        `when`(repository.save(any<DadosUsuarioEntity>())).thenReturn(savedEntity)

        val result = dataBase.save(entity)

        assertNotNull(result.idPessoa)
        assertEquals(savedEntity.nome, result.nome)
        assertEquals(savedEntity.email, result.email)
        assertEquals(savedEntity.documento, result.documento)
        assertEquals(savedEntity.senha, result.senha)
    }

    @Test
    fun `não deve salvar um usuario com sucesso no banco de dados ao conter erros`() {
        val entity = DadosUsuarioEntity(
            nome = "",
            email = "",
            documento = "12345678996",
            senha = "654789"
        )

        val exception = RuntimeException("Falha de conexão ao conter erros")

        `when`(repository.save(any<DadosUsuarioEntity>())).thenThrow(exception)

        val result = assertThrows<DatabaseException> {
            dataBase.save(entity)
        }

        assertTrue(result.message!!.contains("Houve um erro na integração com o banco de dados"))
    }

    @Test
    fun `deve retronar todos os usuarios cadastrados no banco de dados`() {
        val entities = listOf(DadosUsuarioEntity(
            nome = "Nome",
            email = "email@email.com",
            documento = "12345678996",
            senha = "654789"
        ))

        `when`(repository.findAll()).thenReturn(entities)

        val result = dataBase.findAll()

        assertNotNull(result)
    }

    @Test
    fun `não deve retronar usuarios cadastrados no banco de dados`() {
        val exception = RuntimeException("Falha de conexão ao conter erros")

        `when`(repository.findAll()).thenThrow(exception)

        val result = assertThrows<DatabaseException> {
            dataBase.findAll()
        }

        assertTrue(result.message!!.contains("Houve um erro na integração com o banco de dados"))
    }
}