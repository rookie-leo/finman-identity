package br.com.finman.identity.core.usecase.impl

import br.com.finman.identity.adapters.output.database.DataBaseAccess
import br.com.finman.identity.adapters.services.SecurityService
import br.com.finman.identity.core.domain.AccessToken
import br.com.finman.identity.core.domain.DadosLoginDomain
import br.com.finman.identity.core.exceptions.AuthenticationFailedException
import br.com.finman.identity.core.usecase.LoginUseCase
import br.com.finman.identity.utils.toDomain

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