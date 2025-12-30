package com.rookie_leo.pessoa.adapters.input.controllers.requests

import jakarta.validation.constraints.NotBlank

data class DadosUsuarioRequest(
    @field:NotBlank(message = "O campo nome é obrigatório")
    val nome: String,

    @field:NotBlank(message = "O campo email é obrigatório")
    val email: String,

    @field:NotBlank(message = "O campo documento é obrigatório")
    val documento: String,

    @field:NotBlank(message = "O campo senha é obrigatório")
    val senha: String
)
