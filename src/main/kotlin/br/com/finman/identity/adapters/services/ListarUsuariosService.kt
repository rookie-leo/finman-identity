package br.com.finman.identity.adapters.services

import br.com.finman.identity.adapters.input.controllers.responses.DadosUsuarioResponse

interface ListarUsuariosService {
    fun listarPessoas(): List<DadosUsuarioResponse>?
}