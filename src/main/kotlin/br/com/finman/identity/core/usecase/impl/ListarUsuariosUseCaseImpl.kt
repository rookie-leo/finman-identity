package br.com.finman.identity.core.usecase.impl

import br.com.finman.identity.adapters.output.database.DataBaseAccess
import br.com.finman.identity.core.domain.DadosUsuarioDomain
import br.com.finman.identity.core.usecase.ListarUsuariosUseCase
import br.com.finman.identity.utils.toDomain

class ListarUsuariosUseCaseImpl(
    private val dataBaseAccess: DataBaseAccess
): ListarUsuariosUseCase {
    override fun listarUsuarios(): List<DadosUsuarioDomain>? {
        return dataBaseAccess.findAll()?.map { it ->
            it.toDomain()
        }
            ?: emptyList()
    }
}