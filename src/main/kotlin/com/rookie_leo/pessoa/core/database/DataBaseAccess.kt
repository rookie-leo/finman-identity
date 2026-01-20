package com.rookie_leo.pessoa.core.database

import com.rookie_leo.pessoa.adapters.input.repositories.entities.DadosUsuarioEntity
import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain

interface DataBaseAccess {
    fun save(domain: DadosUsuarioDomain): DadosUsuarioEntity
    fun findAll(): List<DadosUsuarioEntity>?
    fun findByEmail(email: String): DadosUsuarioEntity?
}