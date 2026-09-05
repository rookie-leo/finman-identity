package br.com.finman.identity.adapters.input.controllers

import br.com.finman.identity.adapters.input.controllers.requests.DadosLoginRequest
import br.com.finman.identity.domain.AccessToken
import br.com.finman.identity.port.input.LoginUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val loginUseCase: LoginUseCase
) {

    @PostMapping("/login")
    fun login(@RequestBody @Valid dadosLogin: DadosLoginRequest): ResponseEntity<AccessToken> =
        ResponseEntity.ok(loginUseCase.login(dadosLogin.toDomain()))

}