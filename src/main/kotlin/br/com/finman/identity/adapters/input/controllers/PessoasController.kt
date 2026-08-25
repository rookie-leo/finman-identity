package br.com.finman.identity.adapters.input.controllers

import br.com.finman.identity.adapters.input.controllers.requests.DadosLoginRequest
import br.com.finman.identity.adapters.input.controllers.requests.DadosUsuarioRequest
import br.com.finman.identity.adapters.input.controllers.responses.DadosUsuarioResponse
import br.com.finman.identity.adapters.services.CadastroUsuarioService
import br.com.finman.identity.adapters.services.ListarUsuariosService
import br.com.finman.identity.adapters.services.LoginService
import br.com.finman.identity.core.domain.AccessToken
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
    private val cadastroService: CadastroUsuarioService,
    private val loginService: LoginService,
    private val listagemService: ListarUsuariosService
) {

    @PostMapping("/cadastro")
    fun cadastrar(@RequestBody @Valid dadosCadastrais: DadosUsuarioRequest): ResponseEntity<DadosUsuarioResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroService.cadastrar(dadosCadastrais))
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid dadosLogin: DadosLoginRequest): ResponseEntity<AccessToken> {
        return ResponseEntity.status(HttpStatus.OK).body(loginService.login(dadosLogin))
    }

    @GetMapping()
    fun listarUsuarios(): ResponseEntity<List<DadosUsuarioResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(listagemService.listarPessoas())
    }
}