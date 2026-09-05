package br.com.finman.identity.application

import br.com.finman.identity.domain.DadosUsuarioDomain
import br.com.finman.identity.port.input.ListarUsuariosUseCase
import br.com.finman.identity.port.output.UsuarioRepository

class ListarUsuariosService(
    private val usuarioRepository: UsuarioRepository
) : ListarUsuariosUseCase {
    override fun listarUsuarios(): List<DadosUsuarioDomain> = usuarioRepository.findAll()
}
