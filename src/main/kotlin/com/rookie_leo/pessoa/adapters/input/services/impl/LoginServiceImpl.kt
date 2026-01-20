package com.rookie_leo.pessoa.adapters.input.services.impl

import com.rookie_leo.pessoa.adapters.input.controllers.requests.DadosLoginRequest
import com.rookie_leo.pessoa.adapters.input.controllers.responses.DadosUsuarioResponse
import com.rookie_leo.pessoa.adapters.input.services.LoginService
import com.rookie_leo.pessoa.core.usecase.LoginUseCase
import org.springframework.stereotype.Component

@Component
class LoginServiceImpl(
    private val loginUseCase: LoginUseCase
): LoginService {

    override fun login(dadosLogin: DadosLoginRequest): DadosUsuarioResponse? {
        TODO("Not yet implemented")
    }
}