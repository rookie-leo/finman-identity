package com.rookie_leo.pessoa.core.usecase.impl

import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain
import com.rookie_leo.pessoa.core.usecase.CadastroUseCase
import com.rookie_leo.pessoa.output.database.DataBaseAccess

class CadastroUseCaseImpl(
    private val dataBaseAccess: DataBaseAccess
) : CadastroUseCase {
    override fun cadastrar(domain: DadosUsuarioDomain): DadosUsuarioDomain =
        dataBaseAccess.save(domain)


}