package br.com.finman.identity.port.output

import br.com.finman.identity.domain.DadosUsuarioDomain

interface UsuarioRepository {
    fun save(usuario: DadosUsuarioDomain): DadosUsuarioDomain
    fun findAll(): List<DadosUsuarioDomain>
    fun findByEmail(email: String): DadosUsuarioDomain?
}
