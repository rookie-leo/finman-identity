package com.rookie_leo.pessoa.adapters.output.database.impl

import com.rookie_leo.pessoa.adapters.output.database.DataBaseAccess
import com.rookie_leo.pessoa.adapters.output.database.exceptions.DatabaseException
import com.rookie_leo.pessoa.adapters.output.database.repositories.PessoaRepository
import com.rookie_leo.pessoa.adapters.output.database.repositories.entities.DadosUsuarioEntity
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component

@Component
class DataBaseAccessImpl(
    private val pessoaRepository: PessoaRepository
): DataBaseAccess {

    @Transactional
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