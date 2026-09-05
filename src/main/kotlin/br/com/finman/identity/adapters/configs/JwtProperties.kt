package br.com.finman.identity.adapters.configs

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "security.jwt")
data class JwtProperties(
    val privateKeyPath: String,
    val publicKeyPath: String,
    val issuer: String,
    val audience: String,
    val expirationMinutes: Long
)
