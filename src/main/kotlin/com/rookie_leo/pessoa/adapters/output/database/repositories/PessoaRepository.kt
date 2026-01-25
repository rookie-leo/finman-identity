package com.rookie_leo.pessoa.adapters.output.database.repositories

import com.rookie_leo.pessoa.adapters.output.database.repositories.entities.DadosUsuarioEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PessoaRepository: JpaRepository<DadosUsuarioEntity, UUID> {
    fun findByEmail(email: String): DadosUsuarioEntity?

    fun existsByDocumento(documento: String): Boolean
}
