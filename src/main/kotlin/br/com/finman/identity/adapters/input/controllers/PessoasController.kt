package br.com.finman.identity.adapters.input.controllers

import br.com.finman.identity.adapters.input.controllers.requests.DadosLoginRequest
import br.com.finman.identity.adapters.input.controllers.requests.DadosUsuarioRequest
import br.com.finman.identity.adapters.input.controllers.responses.DadosUsuarioResponse
import br.com.finman.identity.domain.AccessToken
import br.com.finman.identity.port.input.CadastrarUsuariosUseCase
import br.com.finman.identity.port.input.ListarUsuariosUseCase
import br.com.finman.identity.port.input.LoginUseCase
import jakarta.validation.Valid
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
    private val cadastrarUsuariosUseCase: CadastrarUsuariosUseCase,
    private val loginUseCase: LoginUseCase,
    private val listarUsuariosUseCase: ListarUsuariosUseCase
) {

    @PostMapping("/cadastro")
    fun cadastrar(@RequestBody @Valid dadosCadastrais: DadosUsuarioRequest): ResponseEntity<DadosUsuarioResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarUsuariosUseCase.cadastrar(dadosCadastrais.toDomain()).toResponse())
    }

    @GetMapping()
    fun listarUsuarios(): ResponseEntity<List<DadosUsuarioResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(listarUsuariosUseCase.listarUsuarios().map { it.toResponse() })
    }
}
