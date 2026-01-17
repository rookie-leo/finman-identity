package com.rookie_leo.pessoa.adapters.input.configs

import com.rookie_leo.pessoa.adapters.input.repositories.PessoaRepository
import com.rookie_leo.pessoa.core.database.DataBaseAccess
import com.rookie_leo.pessoa.core.database.impl.DataBaseAccessImpl
import com.rookie_leo.pessoa.core.usecase.impl.CadastrarUsuarioUseCaseImpl
import com.rookie_leo.pessoa.core.usecase.impl.ListarUsuariosUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfiguration {

    @Bean
    fun cadastroUseCaseImpl(dataBaseAccessImpl: DataBaseAccess): CadastrarUsuarioUseCaseImpl = CadastrarUsuarioUseCaseImpl(dataBaseAccessImpl)

    @Bean
    fun listagemUseCaseImpl(dataBaseAccessImpl: DataBaseAccess): ListarUsuariosUseCaseImpl = ListarUsuariosUseCaseImpl(dataBaseAccessImpl)

    @Bean
    fun dataBaseAccessImpl(pessoaRepository: PessoaRepository): DataBaseAccessImpl = DataBaseAccessImpl(pessoaRepository)
}