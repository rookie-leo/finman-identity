package br.com.finman.identity.adapters.input.controllers.exceptions

data class ApiErrorResponse(
    val errorCode: Int,
    val errorMessage: String,
    val errorsDetails: Map<String, String>? = null
)