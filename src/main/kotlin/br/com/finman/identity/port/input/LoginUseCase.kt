package br.com.finman.identity.port.input

import br.com.finman.identity.domain.AccessToken
import br.com.finman.identity.domain.DadosLoginDomain

interface LoginUseCase {
    fun login(dadosLoginDomain: DadosLoginDomain): AccessToken
}
