package com.rookie_leo.pessoa.input.controllers

import com.rookie_leo.pessoa.input.controllers.requests.DadosUsuarioRequest
import com.rookie_leo.pessoa.input.controllers.responses.DadosUsuarioResponse
import com.rookie_leo.pessoa.input.services.CadastroService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pessoas")
class CadastroController(
    private val cadastroService: CadastroService
) {

    @PostMapping("/cadastro")
    fun cadastrar(@RequestBody dadosCadastrais: DadosUsuarioRequest): ResponseEntity<DadosUsuarioResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroService.cadastrar(dadosCadastrais))
    }

}