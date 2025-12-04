package com.rookie_leo.pessoa.output.database.impl

import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain
import com.rookie_leo.pessoa.output.database.DataBaseAccess
import org.springframework.stereotype.Component

@Component
class DataBaseAccessImpl: DataBaseAccess {
    override fun save(domain: DadosUsuarioDomain): DadosUsuarioDomain {
        TODO("Not yet implemented")
    }
}