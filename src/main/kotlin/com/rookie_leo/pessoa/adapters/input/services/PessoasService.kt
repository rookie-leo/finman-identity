package com.rookie_leo.pessoa.adapters.input.services

import com.rookie_leo.pessoa.adapters.input.controllers.requests.DadosUsuarioRequest
import com.rookie_leo.pessoa.adapters.input.controllers.responses.DadosUsuarioResponse

interface PessoasService {
    fun cadastrar(request: DadosUsuarioRequest): DadosUsuarioResponse
    fun listarPessoas(): List<DadosUsuarioResponse>?

}
