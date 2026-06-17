package com.rookie_leo.pessoa.core.usecase.impl

import com.rookie_leo.pessoa.adapters.output.database.DataBaseAccess
import com.rookie_leo.pessoa.adapters.output.database.repositories.entities.DadosUsuarioEntity
import com.rookie_leo.pessoa.adapters.services.SecurityService
import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain
import com.rookie_leo.pessoa.core.exceptions.DomainValidationException
import com.rookie_leo.pessoa.core.exceptions.DuplicateDocumentoException
import com.rookie_leo.pessoa.core.exceptions.DuplicateEmailException
import com.rookie_leo.pessoa.utils.getDadosUsuarioDomain
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.springframework.dao.DataIntegrityViolationException
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
class CadastrarUsuarioUseCaseImplTest {

    @Mock
    private lateinit var dataBase: DataBaseAccess

    @Mock
    private lateinit var passwordEncoderService: SecurityService

    @InjectMocks
    private lateinit var useCase: CadastrarUsuarioUseCaseImpl

    private lateinit var domain: DadosUsuarioDomain

    @BeforeEach
    fun setup() {
        domain = getDadosUsuarioDomain()
    }

    @Test
    fun `deve cadastrar usuario com sucesso`() {
        val savedEntity = DadosUsuarioEntity(
            idPessoa = domain.pessoaId!!,
            nome = domain.nome,
            email = domain.email,
            documento = domain.documento,
            senha = "HASH_123ABC"
        )

        `when`(passwordEncoderService.encode(any<String>()))
            .thenReturn("HASH_123ABC")
        `when`(dataBase.save(any<DadosUsuarioEntity>())).thenReturn(savedEntity)

        val result = useCase.cadastrar(domain)

        verify(passwordEncoderService, times(1)).encode(domain.senha)
        verify(dataBase, times(1)).save(any<DadosUsuarioEntity>())
        verify(dataBase, times(1)).existsByEmail(anyString())
        verify(dataBase, times(1)).existsByDocumento(anyString())
        assertEquals(domain.nome, result.nome)
        assertEquals(domain.email, result.email)
        assertEquals(domain.documento, result.documento)
        assertEquals("HASH_123ABC", result.senha)
    }

    @Test
    fun `nao deve cadastrar usuario com email ja existente na base de dados`() {
        `when`(dataBase.existsByEmail(any<String>())).thenReturn(true)

        val result = assertThrows<DuplicateEmailException> {
            useCase.cadastrar(domain)
        }

        assertNotNull(result)
        assertEquals(
            "Já existe um usuário cadastrado com este email",
            result.message)
        verify(dataBase, times(1)).existsByEmail(anyString())
        verify(dataBase, times(0)).existsByDocumento(anyString())
        verify(dataBase, times(0)).save(any<DadosUsuarioEntity>())
        verify(passwordEncoderService, times(0)).encode(any<String>())
    }

    @Test
    fun `nao deve cadastrar usuario com documento ja existente na base de dados`() {
        `when`(dataBase.existsByEmail(any<String>())).thenReturn(false)
        `when`(dataBase.existsByDocumento(any<String>())).thenReturn(true)

        val result = assertThrows<DuplicateDocumentoException> {
            useCase.cadastrar(domain)
        }

        assertNotNull(result)
        assertEquals(
            "Já existe um usuário cadastrado com este documento",
            result.message)
        verify(dataBase, times(1)).existsByEmail(anyString())
        verify(dataBase, times(1)).existsByDocumento(anyString())
        verify(dataBase, times(0)).save(any<DadosUsuarioEntity>())
        verify(passwordEncoderService, times(0)).encode(any<String>())
    }

    @Test
    fun `nao deve cadastrar usuario sem nome`() {
        val user = DadosUsuarioDomain(
            nome = "",
            email = domain.email,
            documento = domain.documento,
            senha = domain.senha
        )

        val result = assertThrows<DomainValidationException> {
            useCase.cadastrar(user)
        }

        assertNotNull(result)
        assertEquals("Nome é obrigatório",result.message)
        verify(dataBase, times(0)).existsByEmail(anyString())
        verify(dataBase, times(0)).existsByDocumento(anyString())
        verify(dataBase, times(0)).save(any<DadosUsuarioEntity>())
        verify(passwordEncoderService, times(0)).encode(any<String>())
    }

    @Test
    fun `nao deve cadastrar usuario sem documento`() {
        val user = DadosUsuarioDomain(
            documento = "",
            email = domain.email,
            nome = domain.nome,
            senha = domain.senha
        )

        val result = assertThrows<DomainValidationException> {
            useCase.cadastrar(user)
        }

        assertNotNull(result)
        assertEquals("Documento é obrigatório",result.message)
        verify(dataBase, times(0)).existsByEmail(anyString())
        verify(dataBase, times(0)).existsByDocumento(anyString())
        verify(dataBase, times(0)).save(any<DadosUsuarioEntity>())
        verify(passwordEncoderService, times(0)).encode(any<String>())
    }

    @Test
    fun `nao deve cadastrar usuario sem email`() {
        val user = DadosUsuarioDomain(
            email = "",
            nome = domain.nome,
            documento = domain.documento,
            senha = domain.senha
        )

        val result = assertThrows<DomainValidationException> {
            useCase.cadastrar(user)
        }

        assertNotNull(result)
        assertEquals("Email é obrigatório",result.message)
        verify(dataBase, times(0)).existsByEmail(anyString())
        verify(dataBase, times(0)).existsByDocumento(anyString())
        verify(dataBase, times(0)).save(any<DadosUsuarioEntity>())
        verify(passwordEncoderService, times(0)).encode(any<String>())
    }

    @Test
    fun `nao deve cadastrar usuario sem senha`() {
        val user = DadosUsuarioDomain(
            senha = "",
            email = domain.email,
            documento = domain.documento,
            nome = domain.nome
        )

        val result = assertThrows<DomainValidationException> {
            useCase.cadastrar(user)
        }

        assertNotNull(result)
        assertEquals("Senha é obrigatório",result.message)
        verify(dataBase, times(0)).existsByEmail(anyString())
        verify(dataBase, times(0)).existsByDocumento(anyString())
        verify(dataBase, times(0)).save(any<DadosUsuarioEntity>())
        verify(passwordEncoderService, times(0)).encode(any<String>())
    }

    /**
     * Este teste valida um cenário de concorrência (race condition).
     *
     * Antes de persistir um usuário, a aplicação verifica se já existe um
     * cadastro com o mesmo email ou documento.
     *
     * Entretanto, entre a validação e a operação de escrita no banco de dados,
     * outra requisição concorrente pode cadastrar o mesmo usuário.
     *
     * Exemplo:
     *
     * Requisição A -> existsByEmail() = false
     * Requisição B -> existsByEmail() = false
     * Requisição A -> save()
     * Requisição B -> save() -> DataIntegrityViolationException
     *
     * Por esse motivo a aplicação não deve confiar apenas nas validações
     * realizadas antes da persistência.
     *
     * A constraint UNIQUE do banco de dados continua sendo a última linha
     * de defesa contra duplicidades.
     *
     * Este teste garante que, caso o banco detecte uma violação de integridade
     * durante a gravação, a exceção técnica seja convertida para uma exceção
     * de negócio compreensível para as demais camadas da aplicação.
     */
    @Test
    fun `deve lancar DataIntegrityViolationException`() {
        `when`(dataBase.existsByEmail(any<String>())).thenReturn(false)
        `when`(dataBase.existsByDocumento(any<String>())).thenReturn(false)
        `when`(passwordEncoderService.encode(domain.senha)).thenReturn("HASH_123ABC")
        `when`(dataBase.save(any<DadosUsuarioEntity>())).thenThrow(DataIntegrityViolationException("Já existe um usuário cadastrado com essas informações"))

        val result = assertThrows<DuplicateEmailException> {
            useCase.cadastrar(domain)
        }

        assertNotNull(result)
        assertEquals(
            "Já existe um usuário cadastrado com essas informações: Já existe um usuário cadastrado com essas informações",
            result.message)
        verify(dataBase, times(1)).existsByEmail(anyString())
        verify(dataBase, times(1)).existsByDocumento(anyString())
        verify(dataBase, times(1)).save(any<DadosUsuarioEntity>())
        verify(passwordEncoderService, times(1)).encode(any<String>())
    }
}