package br.com.finman.identity.application

import br.com.finman.identity.domain.DadosUsuarioDomain
import br.com.finman.identity.port.output.UsuarioRepository
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class ListarUsuariosServiceTest {
    private val usuarioRepository = mock<UsuarioRepository>()
    private val service = ListarUsuariosService(usuarioRepository)

    @Test
    fun `deve retornar usuarios fornecidos pelo repositorio`() {
        val usuarios = listOf(DadosUsuarioDomain(nome = "Leonardo", email = "leo@finman.com", documento = "123", senha = "hash"))
        whenever(usuarioRepository.findAll()).thenReturn(usuarios)

        assertEquals(usuarios, service.listarUsuarios())
        verify(usuarioRepository).findAll()
    }
}
