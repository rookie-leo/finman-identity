package com.rookie_leo.pessoa.adapters.services

import com.rookie_leo.pessoa.adapters.input.controllers.requests.DadosLoginRequest
import com.rookie_leo.pessoa.core.domain.AccessToken

interface LoginService {
    fun login(dadosLogin: DadosLoginRequest): AccessToken
}