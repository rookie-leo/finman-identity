package com.rookie_leo.pessoa.core.database

import com.rookie_leo.pessoa.adapters.input.repositories.entities.DadosUsuarioEntity

interface DataBaseAccess {
    fun save(entity: DadosUsuarioEntity): DadosUsuarioEntity
    fun findAll(): List<DadosUsuarioEntity>?
    fun findByEmail(email: String): DadosUsuarioEntity?
}