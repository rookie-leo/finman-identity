package com.rookie_leo.pessoa.output.database

import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain

interface DataBaseAccess {
    fun save(domain: DadosUsuarioDomain): DadosUsuarioDomain

}