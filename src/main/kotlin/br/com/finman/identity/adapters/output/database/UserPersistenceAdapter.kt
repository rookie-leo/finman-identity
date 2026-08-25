package br.com.finman.identity.adapters.output.database

import br.com.finman.identity.adapters.output.database.exceptions.DatabaseException
import br.com.finman.identity.adapters.output.database.repositories.PessoaRepository
import br.com.finman.identity.adapters.output.database.repositories.entities.DadosUsuarioEntity
import br.com.finman.identity.domain.DadosUsuarioDomain
import br.com.finman.identity.port.output.UsuarioRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserPersistenceAdapter(
    private val pessoaRepository: PessoaRepository
) : UsuarioRepository {
    @Transactional
    override fun save(usuario: DadosUsuarioDomain): DadosUsuarioDomain =
        try {
            pessoaRepository.save(usuario.toEntity()).toDomain()
        } catch (ex: Exception) {
            throw DatabaseException("Houve um erro na integração com o banco de dados: ${ex.message}")
        }

    override fun findAll(): List<DadosUsuarioDomain> =
        try {
            pessoaRepository.findAll().map(DadosUsuarioEntity::toDomain)
        } catch (ex: Exception) {
            throw DatabaseException("Houve um erro na integração com o banco de dados: ${ex.message}")
        }

    override fun findByEmail(email: String): DadosUsuarioDomain? =
        pessoaRepository.findByEmail(email)?.toDomain()
}

private fun DadosUsuarioEntity.toDomain() = DadosUsuarioDomain(
    pessoaId = idPessoa,
    nome = nome,
    email = email,
    documento = documento,
    senha = senha
)

private fun DadosUsuarioDomain.toEntity() = DadosUsuarioEntity(
    idPessoa = pessoaId ?: java.util.UUID.randomUUID(),
    nome = nome,
    email = email,
    documento = documento,
    senha = senha
)
