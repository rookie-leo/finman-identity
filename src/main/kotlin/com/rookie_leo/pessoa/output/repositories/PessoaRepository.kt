package com.rookie_leo.pessoa.output.repositories

import com.rookie_leo.pessoa.output.repositories.entities.DadosUsuarioEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PessoaRepository: JpaRepository<DadosUsuarioEntity, UUID> {
    fun findByEmail(email: String): DadosUsuarioEntity?

    fun existsByDocumento(documento: String): Boolean
}
