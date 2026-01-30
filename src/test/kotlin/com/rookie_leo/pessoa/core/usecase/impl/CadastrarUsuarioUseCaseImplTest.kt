package com.rookie_leo.pessoa.core.usecase.impl

import com.rookie_leo.pessoa.adapters.output.database.repositories.entities.DadosUsuarioEntity
import com.rookie_leo.pessoa.adapters.output.database.DataBaseAccess
import com.rookie_leo.pessoa.adapters.services.SecurityService
import com.rookie_leo.pessoa.utils.getDadosUsuarioDomain
import com.rookie_leo.pessoa.utils.toEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.kotlin.any
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.eq
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
class CadastrarUsuarioUseCaseImplTest {

    @Mock
    private lateinit var dataBase: DataBaseAccess

    @Mock
    private lateinit var passwordEncoderService: SecurityService

    @InjectMocks
    private lateinit var useCase: CadastrarUsuarioUseCaseImpl

    @Test
    fun `deve cadastrar usuario com sucesso`() {
        val domain = getDadosUsuarioDomain()
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

        assertEquals(domain.nome, result.nome)
        assertEquals(domain.email, result.email)
        assertEquals(domain.documento, result.documento)
        assertEquals("HASH_123ABC", result.senha)
    }

}