package br.com.finman.identity.adapters.configs

import br.com.finman.identity.adapters.output.database.DataBaseAccess
import br.com.finman.identity.adapters.services.SecurityService
import br.com.finman.identity.core.usecase.impl.CadastrarUsuarioUseCaseImpl
import br.com.finman.identity.core.usecase.impl.ListarUsuariosUseCaseImpl
import br.com.finman.identity.core.usecase.impl.LoginUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfiguration {

    @Bean
    fun cadastroUseCaseImpl(dataBaseAccessImpl: DataBaseAccess, passwordEncoder: SecurityService): CadastrarUsuarioUseCaseImpl =
        CadastrarUsuarioUseCaseImpl(dataBaseAccessImpl, passwordEncoder)

    @Bean
    fun listagemUseCaseImpl(dataBaseAccessImpl: DataBaseAccess): ListarUsuariosUseCaseImpl = ListarUsuariosUseCaseImpl(dataBaseAccessImpl)

    @Bean
    fun loginUseCaseImpl(dataBaseAccess: DataBaseAccess, securityService: SecurityService): LoginUseCaseImpl = LoginUseCaseImpl(dataBaseAccess, securityService)
}