package br.com.finman.identity.application

import br.com.finman.identity.domain.AccessToken
import br.com.finman.identity.domain.AuthenticatedIdentity
import br.com.finman.identity.domain.DadosLoginDomain
import br.com.finman.identity.domain.DadosUsuarioDomain
import br.com.finman.identity.domain.exceptions.AuthenticationFailedException
import br.com.finman.identity.port.output.PasswordHasher
import br.com.finman.identity.port.output.TokenService
import br.com.finman.identity.port.output.UsuarioRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.UUID
import kotlin.test.assertEquals

class LoginServiceTest {
    private val usuarioRepository = mock<UsuarioRepository>()
    private val passwordHasher = mock<PasswordHasher>()
    private val tokenService = mock<TokenService>()
    private val service = LoginService(usuarioRepository, passwordHasher, tokenService)
    private val credentials = DadosLoginDomain("leo@finman.com", "senha")

    @Test
    fun `deve gerar token para credenciais validas`() {
        val id = UUID.randomUUID()
        val usuario = DadosUsuarioDomain(id, "Leonardo", credentials.email, "123", "hash")
        val token = AccessToken("token", expiresInSeconds = 3600)
        whenever(usuarioRepository.findByEmail(credentials.email)).thenReturn(usuario)
        whenever(passwordHasher.matches(credentials.senha, usuario.senha)).thenReturn(true)
        whenever(tokenService.generate(any())).thenReturn(token)

        val resultado = service.login(credentials)

        assertEquals(token, resultado)
        verify(tokenService).generate(AuthenticatedIdentity(id, usuario.email, usuario.nome))
    }

    @Test
    fun `deve rejeitar login quando usuario nao existe`() {
        whenever(usuarioRepository.findByEmail(credentials.email)).thenReturn(null)

        assertThrows<AuthenticationFailedException> { service.login(credentials) }

        verify(passwordHasher, never()).matches(any(), any())
        verify(tokenService, never()).generate(any())
    }

    @Test
    fun `deve rejeitar login quando senha nao confere`() {
        val usuario = DadosUsuarioDomain(UUID.randomUUID(), "Leonardo", credentials.email, "123", "hash")
        whenever(usuarioRepository.findByEmail(credentials.email)).thenReturn(usuario)
        whenever(passwordHasher.matches(credentials.senha, usuario.senha)).thenReturn(false)

        assertThrows<AuthenticationFailedException> { service.login(credentials) }

        verify(tokenService, never()).generate(any())
    }
}
