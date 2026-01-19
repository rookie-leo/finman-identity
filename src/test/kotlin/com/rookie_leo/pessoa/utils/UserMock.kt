package com.rookie_leo.pessoa.utils

import com.rookie_leo.pessoa.adapters.input.controllers.requests.DadosUsuarioRequest
import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain
import java.util.UUID

fun getDadosUsuarioRequest(): DadosUsuarioRequest =
    DadosUsuarioRequest(
        nome = "Teste",
        email = "teste@email.com",
        documento = "12345678900",
        senha = "123456"
    )

fun getDadosUsuarioDomain(request: DadosUsuarioRequest? = null): DadosUsuarioDomain =
    DadosUsuarioDomain(
        pessoaId = UUID.fromString("c9f82670-81a7-4e60-9677-63cb7b9d147a"),
        nome = request?.nome ?: "Teste",
        email = request?.email ?: "teste@teste.com",
        documento = request?.documento ?: "98765435432",
        senha = request?.senha ?: "654789"
    )