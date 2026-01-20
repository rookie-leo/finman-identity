package com.rookie_leo.pessoa.adapters.input.controllers.requests

import jakarta.validation.constraints.NotBlank

data class DadosLoginRequest(
    @field:NotBlank(message = "O campo email é obrigatório")
    val email: String,

    @field:NotBlank(message = "O campo senha é obrigatório")
    val senha: String
)
