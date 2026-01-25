package com.rookie_leo.pessoa.adapters.services.impl

import com.rookie_leo.pessoa.adapters.input.controllers.requests.DadosLoginRequest
import com.rookie_leo.pessoa.core.domain.AccessToken
import com.rookie_leo.pessoa.adapters.input.controllers.responses.DadosUsuarioResponse
import com.rookie_leo.pessoa.adapters.services.LoginService
import com.rookie_leo.pessoa.core.usecase.LoginUseCase
import com.rookie_leo.pessoa.utils.toDomain
import com.rookie_leo.pessoa.utils.toResponse
import org.springframework.stereotype.Component

@Component
class LoginServiceImpl(
    private val loginUseCase: LoginUseCase
): LoginService {

    override fun login(dadosLogin: DadosLoginRequest): DadosUsuarioResponse? {
        return loginUseCase.login(dadosLogin.toDomain()).toResponse()
    }
}