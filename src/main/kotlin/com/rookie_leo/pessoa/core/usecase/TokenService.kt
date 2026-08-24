package com.rookie_leo.pessoa.core.usecase

import com.rookie_leo.pessoa.core.domain.TokenData
import com.rookie_leo.pessoa.core.domain.AccessToken
import com.rookie_leo.pessoa.core.domain.AuthenticatedIdentity

interface TokenService {
    fun generate(identity: AuthenticatedIdentity): AccessToken
    fun validate(token: String): TokenData
}