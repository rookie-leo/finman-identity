package br.com.finman.identity.application

import br.com.finman.identity.domain.DadosUsuarioDomain
import br.com.finman.identity.port.input.CadastrarUsuariosUseCase
import br.com.finman.identity.port.output.PasswordHasher
import br.com.finman.identity.port.output.UsuarioRepository

class CadastrarUsuarioService(
    private val usuarioRepository: UsuarioRepository,
    private val passwordHasher: PasswordHasher
) : CadastrarUsuariosUseCase {
    override fun cadastrar(domain: DadosUsuarioDomain): DadosUsuarioDomain =
        usuarioRepository.save(domain.copy(senha = passwordHasher.hash(domain.senha)))
}
