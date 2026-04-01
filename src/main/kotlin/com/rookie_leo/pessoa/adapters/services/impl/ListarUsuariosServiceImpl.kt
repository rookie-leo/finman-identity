package com.rookie_leo.pessoa.adapters.services.impl

import com.rookie_leo.pessoa.adapters.input.controllers.responses.DadosUsuarioResponse
import com.rookie_leo.pessoa.adapters.services.ListarUsuariosService
import com.rookie_leo.pessoa.core.usecase.ListarUsuariosUseCase
import com.rookie_leo.pessoa.utils.toResponse
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