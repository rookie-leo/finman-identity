package com.rookie_leo.pessoa.core.usecase.impl

import com.rookie_leo.pessoa.core.database.DataBaseAccess
import com.rookie_leo.pessoa.core.domain.DadosLoginDomain
import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain
import com.rookie_leo.pessoa.core.usecase.LoginUseCase
import com.rookie_leo.pessoa.utils.toDomain
import javax.naming.AuthenticationException

class LoginUseCaseImpl(
    private val dataBaseAccess: DataBaseAccess
) : LoginUseCase {
    override fun login(dadosLoginDomain: DadosLoginDomain): DadosUsuarioDomain {
        val userDomain = dataBaseAccess.findByEmail(dadosLoginDomain.email)
            ?: throw AuthenticationException("Usuario ou senha invalido")

        if (dadosLoginDomain.senha != userDomain.senha) throw AuthenticationException("Usuario ou senha invalido")

        return userDomain.toDomain()
    }
}