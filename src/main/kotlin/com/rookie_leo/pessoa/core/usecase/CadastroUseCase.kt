package com.rookie_leo.pessoa.core.usecase

import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain

interface CadastroUseCase {
    fun cadastrar(domain: DadosUsuarioDomain): DadosUsuarioDomain

}
