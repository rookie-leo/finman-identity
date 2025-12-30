package com.rookie_leo.pessoa.core.domain

import java.util.UUID

data class DadosUsuarioDomain(
    var pessoaId: UUID? = null,
    val nome: String,
    val email: String,
    val documento: String,
    val senha: String
)