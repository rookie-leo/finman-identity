package com.rookie_leo.pessoa.adapters.input.controllers

import com.rookie_leo.pessoa.adapters.input.controllers.requests.DadosUsuarioRequest
import com.rookie_leo.pessoa.adapters.input.controllers.responses.DadosUsuarioResponse
import com.rookie_leo.pessoa.adapters.services.CadastroUsuarioService
import com.rookie_leo.pessoa.adapters.services.ListarUsuariosService
import com.rookie_leo.pessoa.adapters.services.LoginService
import com.rookie_leo.pessoa.utils.getDadosUsuarioRequest
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import tools.jackson.databind.ObjectMapper
import java.util.*


@WebMvcTest(PessoasController::class)
class PessoasControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var cadastroService: CadastroUsuarioService

    @MockitoBean
    private lateinit var listagemService: ListarUsuariosService

    @MockitoBean
    private lateinit var loginService: LoginService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `deve cadastrar uma pessoa com sucesso`() {
        val request = getDadosUsuarioRequest()

        val response = DadosUsuarioResponse(
            pessoaId = UUID.randomUUID().toString(),
            nome = "Teste",
            email = "teste@email.com"
        )

        `when`(cadastroService.cadastrar(request)).thenReturn(response)

        mockMvc.perform(
            post("/pessoas/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(status().isCreated)
    }

    @Test
    fun `deve retornar erro 400 ao conter campo invalido`() {
        val invalidRequest = DadosUsuarioRequest(
            nome = "",
            email = "",
            documento = "12345678900",
            senha = "123456"
        )

        mockMvc.perform(
            post("/pessoas/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest))
        )
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.errorCode").value(400))
            .andExpect(jsonPath("$.errorMessage").value("Erro de validação"))
            .andExpect(jsonPath("$.errorsDetails.nome").value("O campo nome é obrigatório"))
            .andExpect(jsonPath("$.errorsDetails.email").value("O campo email é obrigatório"))
    }

}