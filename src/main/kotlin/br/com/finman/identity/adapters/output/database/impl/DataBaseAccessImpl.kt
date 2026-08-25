package br.com.finman.identity.adapters.output.database.impl

import br.com.finman.identity.adapters.output.database.DataBaseAccess
import br.com.finman.identity.adapters.output.database.exceptions.DatabaseException
import br.com.finman.identity.adapters.output.database.repositories.PessoaRepository
import br.com.finman.identity.adapters.output.database.repositories.entities.DadosUsuarioEntity
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

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