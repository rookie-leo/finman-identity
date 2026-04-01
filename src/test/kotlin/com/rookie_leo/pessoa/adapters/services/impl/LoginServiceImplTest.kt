package com.rookie_leo.pessoa.adapters.services.impl

import com.rookie_leo.pessoa.adapters.input.controllers.requests.DadosLoginRequest
import com.rookie_leo.pessoa.core.domain.AccessToken
import com.rookie_leo.pessoa.core.exceptions.AuthenticationFailedException
import com.rookie_leo.pessoa.core.usecase.LoginUseCase
import com.rookie_leo.pessoa.utils.toDomain
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
        val accessToken = AccessToken("eyJhbGciOiJIUzI1Ni...")

        `when`(loginUseCase.login(request.toDomain())).thenReturn(accessToken)

        val result = loginServiceImpl.login(request)

        assertNotNull(result.token)
        assertEquals(accessToken.token, result.token)
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