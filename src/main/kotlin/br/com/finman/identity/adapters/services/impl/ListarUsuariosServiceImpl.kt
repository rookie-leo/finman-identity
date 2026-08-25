package br.com.finman.identity.adapters.services.impl

import br.com.finman.identity.adapters.input.controllers.responses.DadosUsuarioResponse
import br.com.finman.identity.adapters.services.ListarUsuariosService
import br.com.finman.identity.core.usecase.ListarUsuariosUseCase
import br.com.finman.identity.utils.toResponse
import org.springframework.stereotype.Component

@Component
class ListarUsuariosServiceImpl(
    private val useCase: ListarUsuariosUseCase
): ListarUsuariosService {
    override fun listarPessoas(): List<DadosUsuarioResponse>? =
        useCase.listarUsuarios()?.map { it ->
            it.toResponse()
        }
            ?: emptyList()
}