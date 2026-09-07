package br.com.finman.identity.adapters.input.controllers

import br.com.finman.identity.adapters.input.controllers.responses.DadosUsuarioResponse
import br.com.finman.identity.port.input.ListarUsuariosUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pessoas")
class PessoasController(
    private val listarUsuariosUseCase: ListarUsuariosUseCase
) {

    @GetMapping()
    fun listarUsuarios(): ResponseEntity<List<DadosUsuarioResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(listarUsuariosUseCase.listarUsuarios().map { it.toResponse() })
    }
}
