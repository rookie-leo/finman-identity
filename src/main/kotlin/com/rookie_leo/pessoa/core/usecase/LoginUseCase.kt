package com.rookie_leo.pessoa.core.usecase

import com.rookie_leo.pessoa.core.domain.AccessToken
import com.rookie_leo.pessoa.core.domain.DadosLoginDomain

interface LoginUseCase {
    fun login(dadosLoginDomain: DadosLoginDomain): AccessToken
}