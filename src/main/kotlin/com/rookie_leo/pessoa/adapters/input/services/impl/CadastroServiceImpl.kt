package com.rookie_leo.pessoa.adapters.input.services.impl

import com.rookie_leo.pessoa.core.usecase.PessoasUseCase
import com.rookie_leo.pessoa.adapters.input.controllers.requests.DadosUsuarioRequest
import com.rookie_leo.pessoa.adapters.input.controllers.responses.DadosUsuarioResponse
import com.rookie_leo.pessoa.adapters.input.services.PessoasService
import com.rookie_leo.pessoa.utils.toDomain
import com.rookie_leo.pessoa.utils.toResponse
import org.springframework.stereotype.Component

@Component
class CadastroServiceImpl(
    private val useCase: PessoasUseCase
) : PessoasService {

    override fun cadastrar(request: DadosUsuarioRequest): DadosUsuarioResponse =
        useCase.cadastrar(request.toDomain()).toResponse()

    override fun listarPessoas(): List<DadosUsuarioResponse>? =
        useCase.listarUsuarios()?.map { it ->
            it.toResponse()
        }
            ?: emptyList()

}
