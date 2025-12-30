package com.rookie_leo.pessoa.core.usecase

import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain

interface PessoasUseCase {
    fun cadastrar(domain: DadosUsuarioDomain): DadosUsuarioDomain
    fun listarUsuarios(): List<DadosUsuarioDomain>?

}
