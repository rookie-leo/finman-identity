package com.rookie_leo.pessoa.input.services.impl

import com.rookie_leo.pessoa.core.usecase.CadastroUseCase
import com.rookie_leo.pessoa.input.controllers.requests.DadosUsuarioRequest
import com.rookie_leo.pessoa.input.controllers.responses.DadosUsuarioResponse
import com.rookie_leo.pessoa.input.services.CadastroService
import com.rookie_leo.pessoa.utils.toDomain
import com.rookie_leo.pessoa.utils.toResponse
import org.springframework.stereotype.Component

@Component
class CadastroServiceImpl(
    private val cadastroUseCase: CadastroUseCase
): CadastroService {

    override fun cadastrar(request: DadosUsuarioRequest): DadosUsuarioResponse =
        cadastroUseCase.cadastrar(request.toDomain()).toResponse()
}
