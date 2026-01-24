package com.rookie_leo.pessoa.core.usecase

import com.rookie_leo.pessoa.core.domain.DadosLoginDomain
import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain

interface LoginUseCase {
    fun login(dadosLoginDomain: DadosLoginDomain): DadosUsuarioDomain
}