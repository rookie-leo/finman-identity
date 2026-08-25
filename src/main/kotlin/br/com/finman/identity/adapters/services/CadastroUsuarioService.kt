package br.com.finman.identity.adapters.services

import br.com.finman.identity.adapters.input.controllers.requests.DadosUsuarioRequest
import br.com.finman.identity.adapters.input.controllers.responses.DadosUsuarioResponse

interface CadastroUsuarioService {
    fun cadastrar(request: DadosUsuarioRequest): DadosUsuarioResponse
}
