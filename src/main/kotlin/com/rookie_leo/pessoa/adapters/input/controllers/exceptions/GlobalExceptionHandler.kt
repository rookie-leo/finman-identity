package com.rookie_leo.pessoa.adapters.input.controllers.exceptions

import com.fasterxml.jackson.databind.exc.ValueInstantiationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.sql.SQLIntegrityConstraintViolationException
import javax.naming.AuthenticationException

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ApiErrorResponse> {
        val errorsMap = ex.bindingResult.fieldErrors.associate { fieldError ->
            fieldError.field to (fieldError.defaultMessage ?: "Valor invalido")
        }

        val response = ApiErrorResponse(
            errorCode = HttpStatus.BAD_REQUEST.value(),
            errorMessage = "Erro de validação",
            errorsDetails = errorsMap
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(AuthenticationException::class)
    fun handleAuthenticationException(ex: AuthenticationException): ResponseEntity<ApiErrorResponse> =
        ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            ApiErrorResponse(
                errorCode = HttpStatus.FORBIDDEN.value(),
                errorMessage = ex.message ?: "Usuario ou senha invalido"
            )
        )

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(
        ex: HttpMessageNotReadableException
    ): ResponseEntity<ApiErrorResponse> {
        val parameterName = extractMissingParameter(ex)

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                ApiErrorResponse(
                    errorCode = HttpStatus.BAD_REQUEST.value(),
                    errorMessage = parameterName?.let {
                        "Campo obrigatório ausente ou nulo: $it"
                    } ?: "Erro ao processar o corpo da requisição"
                )
            )
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException::class)
    fun handleSQLIntegrityConstraintViolationException(ex: SQLIntegrityConstraintViolationException): ResponseEntity<ApiErrorResponse> =
        ResponseEntity.status(HttpStatus.CONFLICT).body(
            ApiErrorResponse(
                errorCode = HttpStatus.CONFLICT.value(),
                errorMessage = ex.message ?: "E-mail já cadastrado na base de dados"
            )
        )

    private fun extractMissingParameter(ex: HttpMessageNotReadableException): String? {
        val cause = ex.cause

        if (cause is ValueInstantiationException) {
            val message = cause.message ?: return null
            val regex = Regex("parameter ([a-zA-Z0-9_]+)")
            val match = regex.find(message)

            return match?.groupValues?.get(1)
        }

        return null
    }


}