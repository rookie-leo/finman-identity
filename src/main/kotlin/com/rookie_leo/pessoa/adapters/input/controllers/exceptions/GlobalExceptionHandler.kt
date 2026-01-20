package com.rookie_leo.pessoa.adapters.input.controllers.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
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
        ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiErrorResponse(
            errorCode = HttpStatus.FORBIDDEN.value(),
            errorMessage = ex.message ?: "Usuario ou senha invalido"
        ))

}