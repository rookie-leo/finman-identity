package br.com.finman.identity.adapters.services

import br.com.finman.identity.adapters.output.database.repositories.entities.DadosUsuarioEntity
import br.com.finman.identity.core.domain.AccessToken
import br.com.finman.identity.core.domain.DadosLoginDomain
import br.com.finman.identity.core.domain.DadosUsuarioDomain

interface SecurityService {
    fun encode(rawPassword: String): String
    fun authenticate(rawPassword: String, dadosUsuarioDomain: DadosUsuarioDomain): AccessToken?
}