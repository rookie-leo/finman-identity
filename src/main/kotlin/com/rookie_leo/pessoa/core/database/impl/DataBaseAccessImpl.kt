package com.rookie_leo.pessoa.core.database.impl

import com.rookie_leo.pessoa.core.database.DataBaseAccess
import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain
import com.rookie_leo.pessoa.core.exceptions.DatabaseException
import com.rookie_leo.pessoa.output.repositories.PessoaRepository
import com.rookie_leo.pessoa.output.repositories.entities.DadosUsuarioEntity
import com.rookie_leo.pessoa.utils.toEntity

class DataBaseAccessImpl(
    private val pessoaRepository: PessoaRepository
): DataBaseAccess {
    override fun save(domain: DadosUsuarioDomain): DadosUsuarioEntity {
        return try {
            pessoaRepository.save(domain.toEntity())
        } catch (ex: Exception) {
            throw DatabaseException("Houve um erro na integração com o banco de dados", ex.cause)
        }
    }

}