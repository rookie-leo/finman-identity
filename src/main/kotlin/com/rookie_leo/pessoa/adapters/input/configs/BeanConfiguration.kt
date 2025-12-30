package com.rookie_leo.pessoa.adapters.input.configs

import com.rookie_leo.pessoa.adapters.input.repositories.PessoaRepository
import com.rookie_leo.pessoa.core.database.DataBaseAccess
import com.rookie_leo.pessoa.core.database.impl.DataBaseAccessImpl
import com.rookie_leo.pessoa.core.usecase.impl.CadastroUseCaseImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
//@ComponentScan(basePackageClasses = PessoaApplication.class)
class BeanConfiguration {

    @Bean
    fun cadastroUseCaseImpl(dataBaseAccessImpl: DataBaseAccess): CadastroUseCaseImpl = CadastroUseCaseImpl(dataBaseAccessImpl)

    @Bean
    fun dataBaseAccessImpl(pessoaRepository: PessoaRepository): DataBaseAccessImpl = DataBaseAccessImpl(pessoaRepository)
}