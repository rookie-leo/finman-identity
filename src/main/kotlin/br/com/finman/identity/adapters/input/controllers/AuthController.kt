package br.com.finman.identity.adapters.input.controllers

import br.com.finman.identity.adapters.input.controllers.requests.DadosLoginRequest
import br.com.finman.identity.adapters.input.controllers.requests.DadosUsuarioRequest
import br.com.finman.identity.adapters.input.controllers.responses.DadosUsuarioResponse
import br.com.finman.identity.domain.AccessToken
import br.com.finman.identity.port.input.CadastrarUsuariosUseCase
import br.com.finman.identity.port.input.LoginUseCase
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val loginUseCase: LoginUseCase,
    private val cadastrarUsuariosUseCase: CadastrarUsuariosUseCase,
) {

    @PostMapping("/login")
    fun login(@RequestBody @Valid dadosLogin: DadosLoginRequest): ResponseEntity<AccessToken> =
        ResponseEntity.ok(loginUseCase.login(dadosLogin.toDomain()))

    @PostMapping("/register")
    fun cadastrar(@RequestBody @Valid dadosCadastrais: DadosUsuarioRequest): ResponseEntity<DadosUsuarioResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarUsuariosUseCase.cadastrar(dadosCadastrais.toDomain()).toResponse())
    }
}