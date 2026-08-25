package br.com.finman.identity.domain

data class AccessToken(
    val value: String,
    val type: String? = "Bearer",
    val expiresInSeconds: Long
)
