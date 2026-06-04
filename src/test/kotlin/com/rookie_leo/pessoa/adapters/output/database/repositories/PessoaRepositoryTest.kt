package com.rookie_leo.pessoa.adapters.output.database.repositories

import com.rookie_leo.pessoa.adapters.output.database.repositories.entities.DadosUsuarioEntity
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Não testamos métodos de JpaRepository porque eles já são implementados e testados pelo Spring Data.
 * Devemos testar apenas consultas customizadas ou comportamento que contenha lógica própria da aplicação.
 * No caso aqui, é apenas para exemplo e estudos
 * */
@DataJpaTest
class PessoaRepositoryTest {

    @Autowired
    private lateinit var repository: PessoaRepository

    private lateinit var dadosUsuarioEntity: DadosUsuarioEntity

    @BeforeEach
    fun setup() {
        dadosUsuarioEntity = DadosUsuarioEntity(
            idPessoa = UUID.fromString("b2df2c05-3adc-4c3b-9c4c-18baf264a20d"),
            nome = "Nome",
            email = "email@email.com",
            documento = "12345678996",
            senha = "654789"
        )
    }

    @Test
    fun testShouldSavePessoaAndReturnSavedPessoa() {
        val result = repository.save(dadosUsuarioEntity)

        assertNotNull(result.idPessoa)
        assertEquals(dadosUsuarioEntity.nome, result.nome)
        assertEquals(dadosUsuarioEntity.email, result.email)
        assertEquals(dadosUsuarioEntity.documento, result.documento)
        assertEquals(dadosUsuarioEntity.senha, result.senha)
    }

    @Test
    fun testShouldListPersonWhenFindAllReturnPersonList() {
        val dadosUsuarioEntity2 = DadosUsuarioEntity(
            nome = "Nome2",
            email = "email2@email.com",
            documento = "98765432196",
            senha = "123456"
        )

        repository.save(dadosUsuarioEntity2)
        repository.save(dadosUsuarioEntity)

        val personList = repository.findAll()

        assertNotNull(personList)
        assertEquals(2, personList.size)
    }

    @Test
    fun testShouldFindPersonWhenFindPersonById() {
        repository.save(dadosUsuarioEntity)

        val result = repository.findById(dadosUsuarioEntity.idPessoa).get()

        assertNotNull(result)
        assertEquals(dadosUsuarioEntity.nome, result.nome)
        assertEquals(dadosUsuarioEntity.email, result.email)
        assertEquals(dadosUsuarioEntity.documento, result.documento)
        assertEquals(dadosUsuarioEntity.senha, result.senha)
    }

    @Test
    fun testShouldFindPersonWhenFindPersonByEmail() {
        repository.save(dadosUsuarioEntity)

        val result = repository.findByEmail("email@email.com")

        assertNotNull(result)
        assertEquals(dadosUsuarioEntity.nome, result!!.nome)
        assertEquals(dadosUsuarioEntity.email, result.email)
        assertEquals(dadosUsuarioEntity.documento, result.documento)
        assertEquals(dadosUsuarioEntity.senha, result.senha)
    }

    @Test
    fun testShouldRetrunTrueWhenExistsByDocumento() {
        repository.save(dadosUsuarioEntity)

        val result = repository.existsByDocumento(dadosUsuarioEntity.documento)

        assertNotNull(result)
        assertTrue(result)
    }

    @Test
    fun testShouldUpdatePersonWhenPersonAlreadyExists() {
        repository.save(dadosUsuarioEntity)

        val savedPerson = repository.findById(dadosUsuarioEntity.idPessoa).get()
        savedPerson.nome = "Fulano"
        savedPerson.email = "fulano@email.com"
        savedPerson.senha = "1234567898"

        val updatedPerson = repository.findById(dadosUsuarioEntity.idPessoa).get()

        assertNotNull(updatedPerson)
        assertEquals(dadosUsuarioEntity.idPessoa, savedPerson.idPessoa)
        assertEquals("Fulano", updatedPerson.nome)
        assertEquals("fulano@email.com", updatedPerson.email)
        assertEquals("1234567898", updatedPerson.senha)
    }

    @Test
    fun testShouldRemovePersonWhenDelete() {
        val savedPerson = repository.save(dadosUsuarioEntity)

        repository.delete(savedPerson)

        val result = repository.findById(savedPerson.idPessoa)

        assertTrue(result.isEmpty)
    }

    @Test
    fun testShouldFindPersonWhenFindByNomeAndEmail() {
        repository.save(dadosUsuarioEntity)

        val result = repository.findByNomeAndEmail(dadosUsuarioEntity.nome, dadosUsuarioEntity.email)

        assertNotNull(result)
        assertEquals(dadosUsuarioEntity.nome, result.nome)
        assertEquals(dadosUsuarioEntity.email, result.email)
        assertEquals(dadosUsuarioEntity.documento, result.documento)
        assertEquals(dadosUsuarioEntity.senha, result.senha)
    }

    @Test
    fun testShouldFindPersonWhenFindByEmailAndDocumento() {
        repository.save(dadosUsuarioEntity)

        val result = repository.findByEmailAndDocumento(dadosUsuarioEntity.email, dadosUsuarioEntity.documento)

        assertNotNull(result)
        assertEquals(dadosUsuarioEntity.nome, result.nome)
        assertEquals(dadosUsuarioEntity.email, result.email)
        assertEquals(dadosUsuarioEntity.documento, result.documento)
        assertEquals(dadosUsuarioEntity.senha, result.senha)
    }
}