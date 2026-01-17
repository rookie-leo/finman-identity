package com.rookie_leo.pessoa.adapters.input.controllers.exceptions

data class ApiErrorResponse(
    val errorCode: Int,
    val errorMessage: String,
    val errorsDetails: Map<String, String>? = null
)