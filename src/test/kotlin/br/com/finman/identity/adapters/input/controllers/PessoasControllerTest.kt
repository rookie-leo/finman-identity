package br.com.finman.identity.adapters.input.controllers

import br.com.finman.identity.adapters.input.controllers.requests.DadosLoginRequest
import br.com.finman.identity.adapters.input.controllers.requests.DadosUsuarioRequest
import br.com.finman.identity.adapters.input.controllers.responses.DadosUsuarioResponse
import br.com.finman.identity.adapters.services.CadastroUsuarioService
import br.com.finman.identity.adapters.services.ListarUsuariosService
import br.com.finman.identity.adapters.services.LoginService
import br.com.finman.identity.core.exceptions.AuthenticationFailedException
import br.com.finman.identity.utils.getDadosUsuarioRequest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.MethodParameter
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.bind.MethodArgumentNotValidException
import tools.jackson.databind.ObjectMapper
import java.lang.reflect.Method
import java.util.*
import kotlin.test.assertEquals


//@ActiveProfiles("test")
//@ExtendWith(MockitoExtension::class)
class PessoasControllerTest {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private lateinit var pessoasController: PessoasController
    private val cadastroService = mock(CadastroUsuarioService::class.java)
    private val listagemService = mock(ListarUsuariosService::class.java)
    private val loginService = mock(LoginService::class.java)

    @BeforeEach
    fun setup() {
        pessoasController = PessoasController(
            cadastroService,
            loginService,
            listagemService
        )
    }

    @Test
    fun `deve cadastrar uma pessoa com sucesso`() {
        val request = getDadosUsuarioRequest()

        val response = DadosUsuarioResponse(
            pessoaId = UUID.randomUUID().toString(),
            nome = "Teste",
            email = "teste@email.com"
        )

        `when`(cadastroService.cadastrar(any())).thenReturn(response)

        val result = pessoasController.cadastrar(request)

        assertEquals(response.pessoaId, result.body!!.pessoaId)
        assertEquals(response.nome, result.body!!.nome)
        assertEquals(response.email, result.body!!.email)
        Mockito.verify(cadastroService, times(1)).cadastrar(any())
    }

    @Test
    fun `não deve cadastrar usuario repetido no banco de dados`() {

    }

//    @Test
//    fun `Deve realizar o login de usuario cadastrado com sucesso`() {
//        val request = DadosLoginRequest(
//            email = "teste@email.com",
//            senha = "12345"
//        )
//
//        val response = AccessToken("HASH_TOKEN")
//
//        `when`(loginService.login(request)).thenReturn(response)
//
//        mockMvc.perform(
//            post("/pessoas/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(request))
//        )
//            .andExpect { status().is2xxSuccessful }
//            .andExpect { jsonPath("$.token").value("HASH_TOKEN") }
//    }

    @Test
    fun `Não deve realizar o login de usuario quando dados estiverem incorretos`() {
        val request = DadosLoginRequest(
            email = "teste@email.com",
            senha = "12"
        )

        `when`(loginService.login(any())).thenThrow(AuthenticationFailedException())

        assertThrows<AuthenticationFailedException> {
            pessoasController.login(request)
        }
    }
}