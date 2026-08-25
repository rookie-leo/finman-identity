package br.com.finman.identity.core.usecase

import br.com.finman.identity.core.domain.DadosUsuarioDomain

interface CadastrarUsuariosUseCase {
    fun cadastrar(domain: DadosUsuarioDomain): DadosUsuarioDomain
}
