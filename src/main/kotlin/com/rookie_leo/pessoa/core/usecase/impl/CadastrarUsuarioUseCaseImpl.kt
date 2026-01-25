package com.rookie_leo.pessoa.core.usecase.impl

import com.rookie_leo.pessoa.adapters.output.database.DataBaseAccess
import com.rookie_leo.pessoa.adapters.services.SecurityService
import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain
import com.rookie_leo.pessoa.core.usecase.CadastrarUsuariosUseCase
import com.rookie_leo.pessoa.utils.toDomain
import com.rookie_leo.pessoa.utils.toEntity

class CadastrarUsuarioUseCaseImpl(
    private val dataBaseAccess: DataBaseAccess,
    private val passwordEncoder: SecurityService
) : CadastrarUsuariosUseCase {
    override fun cadastrar(domain: DadosUsuarioDomain): DadosUsuarioDomain {
        passwordEncoder.encode(domain)
        return dataBaseAccess.save(domain.toEntity()).toDomain()
    }
}