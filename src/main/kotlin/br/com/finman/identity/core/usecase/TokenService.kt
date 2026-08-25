package br.com.finman.identity.core.usecase

import br.com.finman.identity.core.domain.TokenData
import br.com.finman.identity.core.domain.AccessToken
import br.com.finman.identity.core.domain.AuthenticatedIdentity

interface TokenService {
    fun generate(identity: AuthenticatedIdentity): AccessToken
    fun validate(token: String): TokenData
}