package br.com.finman.identity.port.input

import br.com.finman.identity.domain.DadosUsuarioDomain

interface ListarUsuariosUseCase {
    fun listarUsuarios(): List<DadosUsuarioDomain>
}
