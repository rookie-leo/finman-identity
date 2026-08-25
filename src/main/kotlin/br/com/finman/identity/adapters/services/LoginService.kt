package br.com.finman.identity.adapters.services

import br.com.finman.identity.adapters.input.controllers.requests.DadosLoginRequest
import br.com.finman.identity.core.domain.AccessToken

interface LoginService {
    fun login(dadosLogin: DadosLoginRequest): AccessToken
}