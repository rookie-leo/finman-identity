package com.rookie_leo.pessoa.output.repositories.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.*
import java.io.Serializable
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_PESSOAS")
data class DadosUsuarioEntity(

    @Id
    @field:Column(name = "ID_PESSOA", columnDefinition = "BINARY(16)")
    val idPessoa: UUID? = null,

    @field:Column(nullable = false)
    val nome: String,

    @field:Column(nullable = false)
    val email: String,

    @field:Column(nullable = false)
    val documento: String,

    @JsonIgnore
    @field:Column(nullable = false)
    val senha: String
) : Serializable
