package com.rookie_leo.pessoa.adapters.configs

import com.rookie_leo.pessoa.adapters.output.database.DataBaseAccess
import com.rookie_leo.pessoa.adapters.services.SecurityService
import com.rookie_leo.pessoa.core.usecase.impl.CadastrarUsuarioUseCaseImpl
import com.rookie_leo.pessoa.core.usecase.impl.ListarUsuariosUseCaseImpl
import com.rookie_leo.pessoa.core.usecase.impl.LoginUseCaseImpl
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
    fun loginUseCaseImpl(dataBaseAccess: DataBaseAccess): LoginUseCaseImpl = LoginUseCaseImpl(dataBaseAccess)
}