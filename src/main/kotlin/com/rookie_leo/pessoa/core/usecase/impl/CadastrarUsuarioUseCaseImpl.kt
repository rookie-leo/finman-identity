package com.rookie_leo.pessoa.core.usecase.impl

import com.rookie_leo.pessoa.core.database.DataBaseAccess
import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain
import com.rookie_leo.pessoa.core.usecase.CadastrarUsuariosUseCase
import com.rookie_leo.pessoa.utils.toDomain
import com.rookie_leo.pessoa.utils.toEntity

class CadastrarUsuarioUseCaseImpl(
    private val dataBaseAccess: DataBaseAccess
) : CadastrarUsuariosUseCase {
    override fun cadastrar(domain: DadosUsuarioDomain): DadosUsuarioDomain =
        dataBaseAccess.save(domain.toEntity()).toDomain()
}