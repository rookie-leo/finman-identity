package com.rookie_leo.pessoa.core.usecase.impl

import com.rookie_leo.pessoa.adapters.output.database.DataBaseAccess
import com.rookie_leo.pessoa.adapters.services.SecurityService
import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain
import com.rookie_leo.pessoa.core.exceptions.DomainValidationException
import com.rookie_leo.pessoa.core.exceptions.DuplicateDocumentoException
import com.rookie_leo.pessoa.core.exceptions.DuplicateEmailException
import com.rookie_leo.pessoa.core.usecase.CadastrarUsuariosUseCase
import com.rookie_leo.pessoa.utils.toDomain
import com.rookie_leo.pessoa.utils.toEntity
import org.springframework.dao.DataIntegrityViolationException

class CadastrarUsuarioUseCaseImpl(
    private val dataBaseAccess: DataBaseAccess,
    private val passwordEncoder: SecurityService
) : CadastrarUsuariosUseCase {
    override fun cadastrar(domain: DadosUsuarioDomain): DadosUsuarioDomain {
        require(domain.nome.isNotBlank()) { throw DomainValidationException("Nome é obrigatório") }
        require(domain.documento.isNotBlank()) { throw DomainValidationException("Documento é obrigatório") }
        require(domain.email.isNotBlank()) { throw DomainValidationException("Email é obrigatório") }
        require(domain.senha.isNotBlank()) { throw DomainValidationException("Senha é obrigatório") }

        if (dataBaseAccess.existsByEmail(domain.email)) throw DuplicateEmailException( "Já existe um usuário cadastrado com este email")
        if (dataBaseAccess.existsByDocumento(domain.documento)) throw DuplicateDocumentoException("Já existe um usuário cadastrado com este documento")

        try {
            val encodedPassword = passwordEncoder.encode(domain.senha)
            val domainWithHash = domain.copy(senha = encodedPassword)
            return dataBaseAccess.save(domainWithHash.toEntity()).toDomain()
        } catch (ex: DataIntegrityViolationException) {
            throw DuplicateEmailException("Já existe um usuário cadastrado com essas informações: ${ex.message}")
        }
    }
}