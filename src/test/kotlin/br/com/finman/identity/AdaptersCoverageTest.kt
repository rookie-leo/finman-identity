package br.com.finman.identity

import br.com.finman.identity.adapters.configs.BeanConfiguration
import br.com.finman.identity.adapters.input.controllers.AuthController
import br.com.finman.identity.adapters.input.controllers.PessoasController
import br.com.finman.identity.adapters.input.controllers.requests.DadosLoginRequest
import br.com.finman.identity.adapters.input.controllers.requests.DadosUsuarioRequest
import br.com.finman.identity.adapters.input.controllers.toDomain
import br.com.finman.identity.adapters.input.controllers.toResponse
import br.com.finman.identity.adapters.output.database.UserPersistenceAdapter
import br.com.finman.identity.adapters.output.database.exceptions.DatabaseException
import br.com.finman.identity.adapters.output.database.repositories.PessoaRepository
import br.com.finman.identity.adapters.output.database.repositories.entities.DadosUsuarioEntity
import br.com.finman.identity.adapters.output.security.BCryptPasswordHasher
import br.com.finman.identity.application.CadastrarUsuarioService
import br.com.finman.identity.application.ListarUsuariosService
import br.com.finman.identity.application.LoginService
import br.com.finman.identity.domain.AccessToken
import br.com.finman.identity.domain.DadosLoginDomain
import br.com.finman.identity.domain.DadosUsuarioDomain
import br.com.finman.identity.port.input.CadastrarUsuariosUseCase
import br.com.finman.identity.port.input.ListarUsuariosUseCase
import br.com.finman.identity.port.input.LoginUseCase
import br.com.finman.identity.port.output.PasswordHasher
import br.com.finman.identity.port.output.TokenService
import br.com.finman.identity.port.output.UsuarioRepository
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertNull
import kotlin.test.assertTrue

class AdaptersCoverageTest {
    private val usuario = DadosUsuarioDomain(
        pessoaId = UUID.randomUUID(),
        nome = "Leonardo",
        email = "leo@finman.com",
        documento = "12345678900",
        senha = "senha"
    )

    @Test
    fun `deve mapear requests e respostas da camada web`() {
        val cadastro = DadosUsuarioRequest("Leonardo", "leo@finman.com", "12345678900", "senha")
        val login = DadosLoginRequest("leo@finman.com", "senha")

        assertEquals(usuario.copy(pessoaId = null), cadastro.toDomain())
        assertEquals(DadosLoginDomain(login.email, login.senha), login.toDomain())
        assertEquals("Leonardo", usuario.toResponse().nome)
        assertEquals(usuario.pessoaId.toString(), usuario.toResponse().pessoaId)
    }

    @Test
    fun `deve delegar chamadas dos controllers aos casos de uso`() {
        val cadastroUseCase = mock<CadastrarUsuariosUseCase>()
        val loginUseCase = mock<LoginUseCase>()
        val listagemUseCase = mock<ListarUsuariosUseCase>()
        val pessoasController = PessoasController(cadastroUseCase, loginUseCase, listagemUseCase)
        val authController = AuthController(loginUseCase)
        val cadastro = DadosUsuarioRequest("Leonardo", "leo@finman.com", "12345678900", "senha")
        val token = AccessToken("token", expiresInSeconds = 3600)

        whenever(cadastroUseCase.cadastrar(any())).thenReturn(usuario)
        whenever(listagemUseCase.listarUsuarios()).thenReturn(listOf(usuario))
        whenever(loginUseCase.login(any())).thenReturn(token)

        val cadastroResponse = pessoasController.cadastrar(cadastro)
        val listagemResponse = pessoasController.listarUsuarios()
        val loginResponse = authController.login(DadosLoginRequest("leo@finman.com", "senha"))

        assertEquals(HttpStatus.CREATED, cadastroResponse.statusCode)
        assertEquals(usuario.email, cadastroResponse.body?.email)
        assertEquals(HttpStatus.OK, listagemResponse.statusCode)
        assertEquals(listOf(usuario.email), listagemResponse.body?.map { it.email })
        assertEquals(token, loginResponse.body)
        verify(cadastroUseCase).cadastrar(cadastro.toDomain())
    }

    @Test
    fun `deve adaptar persistencia e encapsular falhas de banco`() {
        val repository = mock<PessoaRepository>()
        val adapter = UserPersistenceAdapter(repository)
        val entity = DadosUsuarioEntity(
            idPessoa = requireNotNull(usuario.pessoaId),
            nome = usuario.nome,
            email = usuario.email,
            documento = usuario.documento,
            senha = usuario.senha
        )

        whenever(repository.save(any<DadosUsuarioEntity>())).thenReturn(entity)
        whenever(repository.findAll()).thenReturn(listOf(entity))
        whenever(repository.findByEmail(usuario.email)).thenReturn(entity)

        assertEquals(usuario, adapter.save(usuario))
        assertEquals(listOf(usuario), adapter.findAll())
        assertEquals(usuario, adapter.findByEmail(usuario.email))
        assertNull(adapter.findByEmail("ausente@finman.com"))

        whenever(repository.save(any<DadosUsuarioEntity>())).thenThrow(IllegalStateException("database unavailable"))
        kotlin.test.assertFailsWith<DatabaseException> { adapter.save(usuario) }

        whenever(repository.findAll()).thenThrow(IllegalStateException("database unavailable"))
        kotlin.test.assertFailsWith<DatabaseException> { adapter.findAll() }
    }

    @Test
    fun `deve criar casos de uso e hashear senhas`() {
        val repository = mock<UsuarioRepository>()
        val passwordHasher = mock<PasswordHasher>()
        val tokenService = mock<TokenService>()
        val configuration = BeanConfiguration()
        val hasher = BCryptPasswordHasher(BCryptPasswordEncoder())

        assertIs<CadastrarUsuarioService>(configuration.cadastroUseCase(repository, passwordHasher))
        assertIs<ListarUsuariosService>(configuration.listagemUseCase(repository))
        assertIs<LoginService>(configuration.loginUseCase(repository, passwordHasher, tokenService))

        val hash = hasher.hash("senha-segura")
        assertTrue(hasher.matches("senha-segura", hash))
        assertFalse(hasher.matches("senha-incorreta", hash))
    }
}
