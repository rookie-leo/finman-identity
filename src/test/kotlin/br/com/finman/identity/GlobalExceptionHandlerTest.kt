package br.com.finman.identity

import br.com.finman.identity.adapters.input.controllers.exceptions.GlobalExceptionHandler
import br.com.finman.identity.domain.exceptions.AuthenticationFailedException
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.mock.http.MockHttpInputMessage
import java.sql.SQLIntegrityConstraintViolationException
import kotlin.test.assertEquals

class GlobalExceptionHandlerTest {
    private val handler = GlobalExceptionHandler()

    @Test
    fun `deve responder erros de autenticacao conflito e corpo invalido`() {
        val authenticationError = handler.handleAuthenticationException(AuthenticationFailedException())
        val databaseError = handler.handleSQLIntegrityConstraintViolationException(
            SQLIntegrityConstraintViolationException("duplicate key")
        )
        val unreadableBodyError = handler.handleHttpMessageNotReadableException(
            HttpMessageNotReadableException("invalid body", MockHttpInputMessage(ByteArray(0)))
        )

        assertEquals(HttpStatus.UNAUTHORIZED, authenticationError.statusCode)
        assertEquals("Usuario ou senha invalido", authenticationError.body?.errorMessage)
        assertEquals(HttpStatus.CONFLICT, databaseError.statusCode)
        assertEquals(HttpStatus.BAD_REQUEST, unreadableBodyError.statusCode)
        assertEquals("Erro ao processar o corpo da requisição", unreadableBodyError.body?.errorMessage)
    }
}
