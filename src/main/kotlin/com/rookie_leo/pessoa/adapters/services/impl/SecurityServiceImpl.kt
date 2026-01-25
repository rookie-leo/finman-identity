package com.rookie_leo.pessoa.adapters.services.impl

import com.rookie_leo.pessoa.adapters.output.database.repositories.entities.DadosUsuarioEntity
import com.rookie_leo.pessoa.core.domain.AccessToken
import com.rookie_leo.pessoa.adapters.services.SecurityService
import com.rookie_leo.pessoa.core.domain.DadosLoginDomain
import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain
import com.rookie_leo.pessoa.utils.toDomain
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import javax.naming.AuthenticationException

@Component
class SecurityServiceImpl(
    private val passwordEncoder: PasswordEncoder
) : SecurityService {
    override fun encode(dadosUsuarioDomain: DadosUsuarioDomain) {
        val password = dadosUsuarioDomain.senha
        val encodedPassword = passwordEncoder.encode(password)
        dadosUsuarioDomain.senha = encodedPassword!!
    }

    override fun authenticate(dadosLoginDomain: DadosLoginDomain, dadosUsuarioEntity: DadosUsuarioEntity): AccessToken? {
        val matches = passwordEncoder.matches(dadosLoginDomain.senha, dadosUsuarioEntity.senha)

        if (!matches) throw AuthenticationException("Usuario ou senha invalido")

        return generateAccessToken(dadosUsuarioEntity.toDomain())
    }

    private fun generateAccessToken(dadosUsuarioDomain: DadosUsuarioDomain): AccessToken =
        AccessToken("")
}