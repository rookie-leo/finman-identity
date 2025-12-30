package com.rookie_leo.pessoa.adapters.input.services

import com.rookie_leo.pessoa.adapters.input.controllers.responses.DadosUsuarioResponse

interface ListarUsuariosService {
    fun listarPessoas(): List<DadosUsuarioResponse>?
}