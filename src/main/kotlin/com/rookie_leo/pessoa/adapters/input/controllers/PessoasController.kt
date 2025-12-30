package com.rookie_leo.pessoa.adapters.input.controllers

import com.rookie_leo.pessoa.adapters.input.controllers.requests.DadosUsuarioRequest
import com.rookie_leo.pessoa.adapters.input.controllers.responses.DadosUsuarioResponse
import com.rookie_leo.pessoa.adapters.input.services.PessoasService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pessoas")
class PessoasController(
    private val service: PessoasService
) {

    @PostMapping("/cadastro")
    fun cadastrar(@RequestBody dadosCadastrais: DadosUsuarioRequest): ResponseEntity<DadosUsuarioResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dadosCadastrais))
    }

    @GetMapping()
    fun listarUsuarios(): ResponseEntity<List<DadosUsuarioResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarPessoas())
    }
}