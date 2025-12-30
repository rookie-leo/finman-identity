package com.rookie_leo.pessoa.core.usecase.impl

import com.rookie_leo.pessoa.core.database.DataBaseAccess
import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain
import com.rookie_leo.pessoa.core.usecase.ListarUsuariosUseCase
import com.rookie_leo.pessoa.utils.toDomain

class ListarUsuariosUseCaseImpl(
    private val dataBaseAccess: DataBaseAccess
): ListarUsuariosUseCase {
    override fun listarUsuarios(): List<DadosUsuarioDomain>? {
        return dataBaseAccess.findAll()?.map { it ->
            it.toDomain()
        }
            ?: emptyList()
    }
}