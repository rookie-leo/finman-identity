package br.com.finman.identity.utils

import br.com.finman.identity.adapters.input.controllers.requests.DadosLoginRequest
import br.com.finman.identity.core.domain.DadosUsuarioDomain
import br.com.finman.identity.adapters.input.controllers.requests.DadosUsuarioRequest
import br.com.finman.identity.adapters.input.controllers.responses.DadosUsuarioResponse
import br.com.finman.identity.adapters.output.database.repositories.entities.DadosUsuarioEntity
import br.com.finman.identity.core.domain.DadosLoginDomain

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

fun DadosLoginRequest.toDomain(): DadosLoginDomain =
    DadosLoginDomain(
        email = email,
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