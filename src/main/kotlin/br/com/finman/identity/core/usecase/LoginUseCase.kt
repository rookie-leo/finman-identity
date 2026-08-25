package br.com.finman.identity.core.usecase

import br.com.finman.identity.core.domain.AccessToken
import br.com.finman.identity.core.domain.DadosLoginDomain

interface LoginUseCase {
    fun login(dadosLoginDomain: DadosLoginDomain): AccessToken
}