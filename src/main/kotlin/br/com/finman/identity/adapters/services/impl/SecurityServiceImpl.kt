package br.com.finman.identity.adapters.services.impl

import br.com.finman.identity.adapters.services.SecurityService
import br.com.finman.identity.adapters.services.jwt.JwtService
import br.com.finman.identity.core.domain.AccessToken
import br.com.finman.identity.core.domain.DadosUsuarioDomain
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class SecurityServiceImpl(
    private val passwordEncoder: PasswordEncoder,
    private val jwt: JwtService
) : SecurityService {
    override fun encode(rawPassword: String): String =
            passwordEncoder.encode(rawPassword)!!

    override fun authenticate(rawPassword: String, dadosUsuarioDomain: DadosUsuarioDomain): AccessToken? {
        if (!passwordEncoder.matches(rawPassword, dadosUsuarioDomain.senha))
            throw BadCredentialsException("Usuario ou senha invalido")

        return jwt.generateAccessToken(dadosUsuarioDomain)
    }
}