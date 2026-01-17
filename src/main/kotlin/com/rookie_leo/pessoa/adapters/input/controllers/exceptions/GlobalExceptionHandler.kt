package com.rookie_leo.pessoa.adapters.input.controllers.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<ApiErrorResponse> {
        val errorsMap = ex.bindingResult.fieldErrors.associate { fieldError ->
            fieldError.field to (fieldError.defaultMessage ?: "Valor invalido")
        }

        val response = ApiErrorResponse(
            errorCode = HttpStatus.UNPROCESSABLE_ENTITY.value(),
            errorMessage = "Erro de validação",
            errorsDetails = errorsMap
        )

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response)
    }

}