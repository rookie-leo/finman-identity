package com.rookie_leo.pessoa.adapters.input.services.impl

import com.rookie_leo.pessoa.core.usecase.CadastrarUsuariosUseCase
import com.rookie_leo.pessoa.adapters.input.controllers.requests.DadosUsuarioRequest
import com.rookie_leo.pessoa.adapters.input.controllers.responses.DadosUsuarioResponse
import com.rookie_leo.pessoa.adapters.input.services.CadastroUsuarioService
import com.rookie_leo.pessoa.utils.toDomain
import com.rookie_leo.pessoa.utils.toResponse
import org.springframework.stereotype.Component

@Component
class CadastroUsuariosServiceImpl(
    private val useCase: CadastrarUsuariosUseCase
) : CadastroUsuarioService {

    override fun cadastrar(request: DadosUsuarioRequest): DadosUsuarioResponse =
        useCase.cadastrar(request.toDomain()).toResponse()
}
