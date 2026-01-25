package com.rookie_leo.pessoa.adapters.services.impl

import com.rookie_leo.pessoa.adapters.input.controllers.responses.DadosUsuarioResponse
import com.rookie_leo.pessoa.adapters.services.impl.CadastroUsuariosServiceImpl
import com.rookie_leo.pessoa.core.usecase.CadastrarUsuariosUseCase
import com.rookie_leo.pessoa.utils.getDadosUsuarioDomain
import com.rookie_leo.pessoa.utils.getDadosUsuarioRequest
import com.rookie_leo.pessoa.utils.toDomain
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class CadastroUsuariosServiceImplTest {
    @Mock
    private lateinit var useCase: CadastrarUsuariosUseCase

    @InjectMocks
    private lateinit var service: CadastroUsuariosServiceImpl

    @Test
    fun `deve cadastrar usuario com sucesso`() {
        val request = getDadosUsuarioRequest()
        val domain = getDadosUsuarioDomain(request)
        val response = DadosUsuarioResponse(
            pessoaId = domain.pessoaId.toString(),
            nome = domain.nome,
            email = domain.email
        )

        `when`(useCase.cadastrar(request.toDomain())).thenReturn(domain)

        val result = service.cadastrar(request)

        assertEquals(result.pessoaId, response.pessoaId)
        assertEquals(result.nome, response.nome)
        assertEquals(result.email, response.email)
    }
}