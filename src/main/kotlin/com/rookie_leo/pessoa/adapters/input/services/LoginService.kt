package com.rookie_leo.pessoa.adapters.input.services

import com.rookie_leo.pessoa.adapters.input.controllers.requests.DadosLoginRequest
import com.rookie_leo.pessoa.adapters.input.controllers.responses.DadosUsuarioResponse

interface LoginService {
    fun login(dadosLogin: DadosLoginRequest): DadosUsuarioResponse?
}