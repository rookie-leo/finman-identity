package com.rookie_leo.pessoa.adapters.services

import com.rookie_leo.pessoa.adapters.output.database.repositories.entities.DadosUsuarioEntity
import com.rookie_leo.pessoa.core.domain.AccessToken
import com.rookie_leo.pessoa.core.domain.DadosLoginDomain
import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain

interface SecurityService {
    fun encode(dadosUsuarioDomain: DadosUsuarioDomain)
    fun authenticate(dadosLoginDomain: DadosLoginDomain, dadosUsuarioEntity: DadosUsuarioEntity): AccessToken?
}