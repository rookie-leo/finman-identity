package br.com.finman.identity.adapters.services.impl

import br.com.finman.identity.core.usecase.CadastrarUsuariosUseCase
import br.com.finman.identity.adapters.input.controllers.requests.DadosUsuarioRequest
import br.com.finman.identity.adapters.input.controllers.responses.DadosUsuarioResponse
import br.com.finman.identity.adapters.services.CadastroUsuarioService
import br.com.finman.identity.utils.toDomain
import br.com.finman.identity.utils.toResponse
import org.springframework.stereotype.Component

@Component
class CadastroUsuariosServiceImpl(
    private val useCase: CadastrarUsuariosUseCase
) : CadastroUsuarioService {

    override fun cadastrar(request: DadosUsuarioRequest): DadosUsuarioResponse =
        useCase.cadastrar(request.toDomain()).toResponse()
}
