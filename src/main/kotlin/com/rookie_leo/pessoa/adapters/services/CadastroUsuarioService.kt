package com.rookie_leo.pessoa.adapters.services

import com.rookie_leo.pessoa.adapters.input.controllers.requests.DadosUsuarioRequest
import com.rookie_leo.pessoa.adapters.input.controllers.responses.DadosUsuarioResponse

interface CadastroUsuarioService {
    fun cadastrar(request: DadosUsuarioRequest): DadosUsuarioResponse
}
