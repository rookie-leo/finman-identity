package br.com.finman.identity.adapters.services.impl

import br.com.finman.identity.adapters.input.controllers.requests.DadosLoginRequest
import br.com.finman.identity.core.domain.AccessToken
import br.com.finman.identity.core.exceptions.AuthenticationFailedException
import br.com.finman.identity.core.usecase.LoginUseCase
import br.com.finman.identity.utils.toDomain
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
class LoginServiceImplTest {
    @Mock
    private lateinit var loginUseCase: LoginUseCase

    @InjectMocks
    private lateinit var loginServiceImpl: LoginServiceImpl

    @Test
    fun `deve realizar o login de um usario com sucesso`() {
        val request = DadosLoginRequest(
            email = "teste@teste.com",
            senha = "123456"
        )
        val accessToken = AccessToken(value = "eyJhbGciOiJIUzI1Ni...", expiresInSeconds = 60)

        `when`(loginUseCase.login(request.toDomain())).thenReturn(accessToken)

        val result = loginServiceImpl.login(request)

        assertNotNull(result)
        assertEquals(accessToken.value, result.value)
        verify(loginUseCase, times(1)).login(any())
    }

    @Test
    fun `deve lançar uma exceção ao tentar realizar login com dados invalidos`() {
        val request = DadosLoginRequest(
            email = "teste@teste.com",
            senha = "123456"
        )

        `when`(loginUseCase.login(request.toDomain())).thenThrow(AuthenticationFailedException())

        val result = assertThrows<AuthenticationFailedException> {
            loginServiceImpl.login(request)
        }

        assertEquals("Usuario ou senha invalido", result.message)
        verify(loginUseCase, times(1)).login(any())
    }
}