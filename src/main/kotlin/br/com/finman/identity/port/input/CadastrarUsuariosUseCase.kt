package br.com.finman.identity.port.input

import br.com.finman.identity.domain.DadosUsuarioDomain

interface CadastrarUsuariosUseCase {
    fun cadastrar(domain: DadosUsuarioDomain): DadosUsuarioDomain
}
