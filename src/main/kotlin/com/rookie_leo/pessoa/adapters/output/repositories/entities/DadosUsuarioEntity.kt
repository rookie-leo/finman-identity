package com.rookie_leo.pessoa.adapters.output.repositories.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.io.Serializable
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_PESSOAS")
data class DadosUsuarioEntity(

    @Id
    @JdbcTypeCode(SqlTypes.BINARY)
    @field:Column(name = "ID_PESSOA", columnDefinition = "BINARY(16)", unique = true)
    var idPessoa: UUID = UUID.randomUUID(),

    @field:Column(nullable = false)
    val nome: String,

    @field:Column(nullable = false, unique = true)
    val email: String,

    @field:Column(nullable = false, unique = true)
    val documento: String,

    @JsonIgnore
    @field:Column(nullable = false)
    val senha: String
) : Serializable
