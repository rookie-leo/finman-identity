package br.com.finman.identity.port.output

import br.com.finman.identity.domain.AccessToken
import br.com.finman.identity.domain.AuthenticatedIdentity

interface TokenService {
    fun generate(identity: AuthenticatedIdentity): AccessToken
    fun validate(token: String): AuthenticatedIdentity
}
