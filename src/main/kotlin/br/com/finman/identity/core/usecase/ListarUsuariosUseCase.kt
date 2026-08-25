package br.com.finman.identity.core.usecase

import br.com.finman.identity.core.domain.DadosUsuarioDomain

interface ListarUsuariosUseCase {
    fun listarUsuarios(): List<DadosUsuarioDomain>?
}