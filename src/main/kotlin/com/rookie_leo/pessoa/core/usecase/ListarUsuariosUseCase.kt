package com.rookie_leo.pessoa.core.usecase

import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain

interface ListarUsuariosUseCase {
    fun listarUsuarios(): List<DadosUsuarioDomain>?
}