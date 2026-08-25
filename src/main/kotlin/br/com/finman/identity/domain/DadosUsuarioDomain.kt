package br.com.finman.identity.domain

import java.util.UUID

data class DadosUsuarioDomain(
    var pessoaId: UUID? = null,
    val nome: String,
    val email: String,
    val documento: String,
    var senha: String
)
