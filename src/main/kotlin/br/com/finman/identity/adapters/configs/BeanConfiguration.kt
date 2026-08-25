package br.com.finman.identity.adapters.configs

import br.com.finman.identity.application.CadastrarUsuarioService
import br.com.finman.identity.application.ListarUsuariosService
import br.com.finman.identity.application.LoginService
import br.com.finman.identity.port.input.CadastrarUsuariosUseCase
import br.com.finman.identity.port.input.ListarUsuariosUseCase
import br.com.finman.identity.port.input.LoginUseCase
import br.com.finman.identity.port.output.PasswordHasher
import br.com.finman.identity.port.output.TokenService
import br.com.finman.identity.port.output.UsuarioRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfiguration {

    @Bean
    fun cadastroUseCase(usuarioRepository: UsuarioRepository, passwordHasher: PasswordHasher): CadastrarUsuariosUseCase =
        CadastrarUsuarioService(usuarioRepository, passwordHasher)

    @Bean
    fun listagemUseCase(usuarioRepository: UsuarioRepository): ListarUsuariosUseCase = ListarUsuariosService(usuarioRepository)

    @Bean
    fun loginUseCase(usuarioRepository: UsuarioRepository, passwordHasher: PasswordHasher, tokenService: TokenService): LoginUseCase =
        LoginService(usuarioRepository, passwordHasher, tokenService)
}
