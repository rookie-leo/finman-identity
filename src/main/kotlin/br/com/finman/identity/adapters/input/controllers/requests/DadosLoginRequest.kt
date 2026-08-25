package br.com.finman.identity.adapters.input.controllers.requests

import br.com.finman.identity.core.domain.DadosLoginDomain
import jakarta.validation.constraints.NotBlank

data class DadosLoginRequest(
    @field:NotBlank(message = "O campo email é obrigatório")
    val email: String,

    @field:NotBlank(message = "O campo senha é obrigatório")
    val senha: String
)
