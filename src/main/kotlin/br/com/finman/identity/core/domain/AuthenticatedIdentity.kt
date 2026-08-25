package br.com.finman.identity.core.domain

import java.util.UUID

data class AuthenticatedIdentity(
    val id: UUID,
    val email: String,
    val nome: String,
    val roles: Set<String> = emptySet()
)
