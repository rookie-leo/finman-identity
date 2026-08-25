package br.com.finman.identity.adapters.services.impl

import br.com.finman.identity.adapters.input.controllers.requests.DadosLoginRequest
import br.com.finman.identity.core.domain.AccessToken
import br.com.finman.identity.adapters.input.controllers.responses.DadosUsuarioResponse
import br.com.finman.identity.adapters.services.LoginService
import br.com.finman.identity.core.usecase.LoginUseCase
import br.com.finman.identity.utils.toDomain
import br.com.finman.identity.utils.toResponse
import org.springframework.stereotype.Component

@Component
class LoginServiceImpl(
    private val loginUseCase: LoginUseCase
): LoginService {

    override fun login(dadosLogin: DadosLoginRequest): AccessToken {
        return loginUseCase.login(dadosLogin.toDomain())
    }
}