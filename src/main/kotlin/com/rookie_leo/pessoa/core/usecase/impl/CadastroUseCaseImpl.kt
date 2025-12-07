package com.rookie_leo.pessoa.core.usecase.impl

import com.rookie_leo.pessoa.core.database.DataBaseAccess
import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain
import com.rookie_leo.pessoa.core.usecase.CadastroUseCase
import com.rookie_leo.pessoa.utils.toDomain

class CadastroUseCaseImpl(
    private val dataBaseAccess: DataBaseAccess
) : CadastroUseCase {
    override fun cadastrar(domain: DadosUsuarioDomain): DadosUsuarioDomain =
        dataBaseAccess.save(domain).toDomain()

}