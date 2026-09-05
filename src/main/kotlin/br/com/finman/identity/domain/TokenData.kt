package br.com.finman.identity.domain

import java.time.Instant
import java.util.UUID

data class TokenData(
    val userId: UUID,
    val email: String,
    val roles: Set<String>,
    val expiresAt: Instant
)
