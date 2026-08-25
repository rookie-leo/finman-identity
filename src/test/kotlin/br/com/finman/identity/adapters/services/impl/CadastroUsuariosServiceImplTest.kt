package br.com.finman.identity.adapters.services.impl

import br.com.finman.identity.adapters.input.controllers.responses.DadosUsuarioResponse
import br.com.finman.identity.adapters.services.impl.CadastroUsuariosServiceImpl
import br.com.finman.identity.core.usecase.CadastrarUsuariosUseCase
import br.com.finman.identity.utils.getDadosUsuarioDomain
import br.com.finman.identity.utils.getDadosUsuarioRequest
import br.com.finman.identity.utils.toDomain
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