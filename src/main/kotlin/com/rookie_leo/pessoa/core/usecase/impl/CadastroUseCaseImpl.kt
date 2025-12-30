package com.rookie_leo.pessoa.core.usecase.impl

import com.rookie_leo.pessoa.core.database.DataBaseAccess
import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain
import com.rookie_leo.pessoa.core.usecase.PessoasUseCase
import com.rookie_leo.pessoa.utils.toDomain

class CadastroUseCaseImpl(
    private val dataBaseAccess: DataBaseAccess
) : PessoasUseCase {
    override fun cadastrar(domain: DadosUsuarioDomain): DadosUsuarioDomain =
        dataBaseAccess.save(domain).toDomain()

    override fun listarUsuarios(): List<DadosUsuarioDomain>? {
        return dataBaseAccess.findAll()?.map { it ->
            it.toDomain()
        }
            ?: emptyList()
    }

}