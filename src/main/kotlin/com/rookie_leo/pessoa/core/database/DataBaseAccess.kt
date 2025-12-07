package com.rookie_leo.pessoa.core.database

import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain
import com.rookie_leo.pessoa.output.repositories.entities.DadosUsuarioEntity

interface DataBaseAccess {
    fun save(domain: DadosUsuarioDomain): DadosUsuarioEntity

}