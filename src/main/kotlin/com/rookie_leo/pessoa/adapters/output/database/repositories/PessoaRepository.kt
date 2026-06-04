package com.rookie_leo.pessoa.adapters.output.database.repositories

import com.rookie_leo.pessoa.adapters.output.database.repositories.entities.DadosUsuarioEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PessoaRepository: JpaRepository<DadosUsuarioEntity, UUID> {
    fun findByEmail(email: String): DadosUsuarioEntity?

    fun existsByDocumento(documento: String): Boolean

    // Custom query using JPQL with index parameters
    @Query("select p from DadosUsuarioEntity p where p.nome = ?1 and p.email = ?2")
    fun findByNomeAndEmail(nome: String, email: String): DadosUsuarioEntity

    // Custom query using JPQL with named parameters
    @Query("select p from DadosUsuarioEntity p where p.email =:email and p.documento =:documento")
    fun findByEmailAndDocumento(
        @Param("email") email: String,
        @Param("documento")documento: String): DadosUsuarioEntity
}
