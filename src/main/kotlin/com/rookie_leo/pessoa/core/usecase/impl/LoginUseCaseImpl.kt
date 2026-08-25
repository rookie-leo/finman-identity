package com.rookie_leo.pessoa.core.usecase.impl

import com.rookie_leo.pessoa.adapters.output.database.DataBaseAccess
import com.rookie_leo.pessoa.adapters.services.SecurityService
import com.rookie_leo.pessoa.core.domain.AccessToken
import com.rookie_leo.pessoa.core.domain.DadosLoginDomain
import com.rookie_leo.pessoa.core.exceptions.AuthenticationFailedException
import com.rookie_leo.pessoa.core.usecase.LoginUseCase
import com.rookie_leo.pessoa.utils.toDomain

class LoginUseCaseImpl(
    private val dataBaseAccess: DataBaseAccess,
    private val securityService: SecurityService
) : LoginUseCase {
    override fun login(dadosLoginDomain: DadosLoginDomain): AccessToken {
        val userEntity = dataBaseAccess.findByEmail(dadosLoginDomain.email)
            ?: throw AuthenticationFailedException("Usuario ou senha invalido")

        return securityService.authenticate(
            dadosLoginDomain.senha,
            userEntity.toDomain()
        ) ?: throw AuthenticationFailedException("Usuario ou senha invalido")
    }
}