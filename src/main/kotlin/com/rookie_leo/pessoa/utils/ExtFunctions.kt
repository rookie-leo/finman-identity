package com.rookie_leo.pessoa.utils

import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain
import com.rookie_leo.pessoa.input.controllers.requests.DadosUsuarioRequest
import com.rookie_leo.pessoa.input.controllers.responses.DadosUsuarioResponse
import com.rookie_leo.pessoa.output.repositories.entities.DadosUsuarioEntity

fun DadosUsuarioEntity.toDomain(): DadosUsuarioDomain =
    DadosUsuarioDomain(
        pessoaId = idPessoa!!,
        nome = nome,
        email = email,
        documento = documento,
        senha = senha
    )

fun DadosUsuarioRequest.toDomain(): DadosUsuarioDomain =
    DadosUsuarioDomain(
        nome = nome,
        email = email,
        documento = documento,
        senha = senha
    )

fun DadosUsuarioDomain.toEntity(): DadosUsuarioEntity =
    DadosUsuarioEntity(
        nome = nome,
        email = email,
        documento = documento,
        senha = senha
    )

fun DadosUsuarioDomain.toResponse(): DadosUsuarioResponse =
    DadosUsuarioResponse(
        pessoaId = pessoaId.toString(),
        email = email,
        nome = nome
    )