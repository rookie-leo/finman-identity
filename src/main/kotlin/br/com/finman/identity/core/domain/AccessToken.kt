package br.com.finman.identity.core.domain

data class AccessToken(
    val value: String,
    val type: String? = "Bearer",
    val expiresInSeconds: Long
)