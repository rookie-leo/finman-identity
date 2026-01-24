package com.rookie_leo.pessoa.core.database.impl

import com.rookie_leo.pessoa.adapters.input.repositories.PessoaRepository
import com.rookie_leo.pessoa.adapters.input.repositories.entities.DadosUsuarioEntity
import com.rookie_leo.pessoa.core.database.DataBaseAccess
import com.rookie_leo.pessoa.core.exceptions.DatabaseException

class DataBaseAccessImpl(
    private val pessoaRepository: PessoaRepository
): DataBaseAccess {
    override fun save(entity: DadosUsuarioEntity): DadosUsuarioEntity {
        return try {
            pessoaRepository.save(entity)
        } catch (ex: Exception) {
            throw DatabaseException("Houve um erro na integração com o banco de dados: ${ex.message}")
        }
    }

    override fun findAll(): List<DadosUsuarioEntity>? {
        return try {
            pessoaRepository.findAll()
        } catch (ex: Exception) {
            throw DatabaseException("Houve um erro na integração com o banco de dados: ${ex.message}")
        }
    }

    override fun findByEmail(email: String): DadosUsuarioEntity? =
        pessoaRepository.findByEmail(email)

}