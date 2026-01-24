package com.rookie_leo.pessoa.adapters.input.controllers.requests

import com.rookie_leo.pessoa.core.domain.DadosLoginDomain
import jakarta.validation.constraints.NotBlank

data class DadosLoginRequest(
    @field:NotBlank(message = "O campo email é obrigatório")
    val email: String,

    @field:NotBlank(message = "O campo senha é obrigatório")
    val senha: String
)
