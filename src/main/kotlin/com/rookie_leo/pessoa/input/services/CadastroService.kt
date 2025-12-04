package com.rookie_leo.pessoa.input.services

import com.rookie_leo.pessoa.input.controllers.requests.DadosUsuarioRequest
import com.rookie_leo.pessoa.input.controllers.responses.DadosUsuarioResponse
import org.springframework.http.ResponseEntity

interface CadastroService {
    fun cadastrar(request: DadosUsuarioRequest): DadosUsuarioResponse

}
