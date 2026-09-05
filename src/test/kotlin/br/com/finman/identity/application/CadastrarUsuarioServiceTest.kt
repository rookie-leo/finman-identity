package br.com.finman.identity.application

import br.com.finman.identity.domain.DadosUsuarioDomain
import br.com.finman.identity.port.output.PasswordHasher
import br.com.finman.identity.port.output.UsuarioRepository
import org.junit.jupiter.api.Test
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class CadastrarUsuarioServiceTest {
    private val usuarioRepository = mock<UsuarioRepository>()
    private val passwordHasher = mock<PasswordHasher>()
    private val service = CadastrarUsuarioService(usuarioRepository, passwordHasher)

    @Test
    fun `deve persistir senha com hash e nunca a senha original`() {
        val usuario = DadosUsuarioDomain(nome = "Leonardo", email = "leo@finman.com", documento = "123", senha = "senha-original")
        whenever(passwordHasher.hash("senha-original")).thenReturn("senha-com-hash")
        whenever(usuarioRepository.save(org.mockito.kotlin.any())).thenAnswer { it.arguments[0] }

        val resultado = service.cadastrar(usuario)
        val usuarioPersistido = argumentCaptor<DadosUsuarioDomain>()

        verify(usuarioRepository).save(usuarioPersistido.capture())
        assertEquals("senha-com-hash", usuarioPersistido.firstValue.senha)
        assertEquals("senha-original", usuario.senha)
        assertEquals("senha-com-hash", resultado.senha)
    }
}
