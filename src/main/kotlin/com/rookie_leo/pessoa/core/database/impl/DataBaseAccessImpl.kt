package com.rookie_leo.pessoa.core.database.impl

import com.rookie_leo.pessoa.adapters.input.repositories.PessoaRepository
import com.rookie_leo.pessoa.adapters.input.repositories.entities.DadosUsuarioEntity
import com.rookie_leo.pessoa.core.database.DataBaseAccess
import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain
import com.rookie_leo.pessoa.core.exceptions.DatabaseException
import com.rookie_leo.pessoa.utils.toEntity

class DataBaseAccessImpl(
    private val pessoaRepository: PessoaRepository
): DataBaseAccess {
    override fun save(domain: DadosUsuarioDomain): DadosUsuarioEntity {
        return try {
            pessoaRepository.save(domain.toEntity())
        } catch (ex: Exception) {
            throw DatabaseException("Houve um erro na integração com o banco de dados: ${ex.message}")
        }
    }

    override fun findAll(): List<DadosUsuarioEntity>? {
        return try {
            pessoaRepository.findAll()
        } catch (ex: Exception) {
            throw DatabaseException("Houve um erro na integração com o banco de dados")
        }
    }

}