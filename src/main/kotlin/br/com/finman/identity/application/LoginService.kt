package br.com.finman.identity.application

import br.com.finman.identity.domain.AccessToken
import br.com.finman.identity.domain.AuthenticatedIdentity
import br.com.finman.identity.domain.DadosLoginDomain
import br.com.finman.identity.domain.exceptions.AuthenticationFailedException
import br.com.finman.identity.port.input.LoginUseCase
import br.com.finman.identity.port.output.PasswordHasher
import br.com.finman.identity.port.output.TokenService
import br.com.finman.identity.port.output.UsuarioRepository

class LoginService(
    private val usuarioRepository: UsuarioRepository,
    private val passwordHasher: PasswordHasher,
    private val tokenService: TokenService
) : LoginUseCase {
    override fun login(dadosLoginDomain: DadosLoginDomain): AccessToken {
        val usuario = usuarioRepository.findByEmail(dadosLoginDomain.email)
            ?: throw AuthenticationFailedException()

        if (!passwordHasher.matches(dadosLoginDomain.senha, usuario.senha)) {
            throw AuthenticationFailedException()
        }

        return tokenService.generate(
            AuthenticatedIdentity(
                id = requireNotNull(usuario.pessoaId) { "Usuario autenticado deve possuir identificador" },
                email = usuario.email,
                nome = usuario.nome
            )
        )
    }
}
