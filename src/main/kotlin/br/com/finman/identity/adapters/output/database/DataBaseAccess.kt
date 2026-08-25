package br.com.finman.identity.adapters.output.database

import br.com.finman.identity.adapters.output.database.repositories.entities.DadosUsuarioEntity

interface DataBaseAccess {
    fun save(entity: DadosUsuarioEntity): DadosUsuarioEntity
    fun findAll(): List<DadosUsuarioEntity>?
    fun findByEmail(email: String): DadosUsuarioEntity?
}