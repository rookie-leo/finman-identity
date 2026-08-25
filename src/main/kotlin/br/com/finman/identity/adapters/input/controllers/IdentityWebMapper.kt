package br.com.finman.identity.adapters.input.controllers

import br.com.finman.identity.adapters.input.controllers.requests.DadosLoginRequest
import br.com.finman.identity.adapters.input.controllers.requests.DadosUsuarioRequest
import br.com.finman.identity.adapters.input.controllers.responses.DadosUsuarioResponse
import br.com.finman.identity.domain.DadosLoginDomain
import br.com.finman.identity.domain.DadosUsuarioDomain

fun DadosUsuarioRequest.toDomain() = DadosUsuarioDomain(
    nome = nome,
    email = email,
    documento = documento,
    senha = senha
)

fun DadosLoginRequest.toDomain() = DadosLoginDomain(email = email, senha = senha)

fun DadosUsuarioDomain.toResponse() = DadosUsuarioResponse(
    pessoaId = pessoaId.toString(),
    email = email,
    nome = nome
)
