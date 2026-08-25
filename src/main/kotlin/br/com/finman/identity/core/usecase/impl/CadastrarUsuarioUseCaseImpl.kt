package br.com.finman.identity.core.usecase.impl

import br.com.finman.identity.adapters.output.database.DataBaseAccess
import br.com.finman.identity.adapters.services.SecurityService
import br.com.finman.identity.core.domain.DadosUsuarioDomain
import br.com.finman.identity.core.usecase.CadastrarUsuariosUseCase
import br.com.finman.identity.utils.toDomain
import br.com.finman.identity.utils.toEntity

class CadastrarUsuarioUseCaseImpl(
    private val dataBaseAccess: DataBaseAccess,
    private val passwordEncoder: SecurityService
) : CadastrarUsuariosUseCase {
    override fun cadastrar(domain: DadosUsuarioDomain): DadosUsuarioDomain {
        val encodedPassword = passwordEncoder.encode(domain.senha)
        val domainWithHash = domain.copy(senha = encodedPassword)
        return dataBaseAccess.save(domainWithHash.toEntity()).toDomain()
    }
}