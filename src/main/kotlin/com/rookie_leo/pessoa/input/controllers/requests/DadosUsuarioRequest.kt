package com.rookie_leo.pessoa.input.controllers.requests

import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain
import jakarta.validation.constraints.NotBlank

class DadosUsuarioRequest(
    @field:NotBlank(message = "O campo nome é obrigatório")
    val nome: String,

    @field:NotBlank(message = "O campo email é obrigatório")
    val email: String,

    @field:NotBlank(message = "O campo documento é obrigatório")
    val documento: String,

    @field:NotBlank(message = "O campo senha é obrigatório")
    val senha: String
) {

    fun toEntity(): DadosUsuarioDomain {
        return DadosUsuarioDomain(
            nome = nome,
            email = email,
            documento = documento,
            senha = senha
        )
    }
}
