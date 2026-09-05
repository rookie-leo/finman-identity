package br.com.finman.identity.adapters.configs

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app.cors")
data class CorsProperties(
    val allowedOrigins: List<String> = emptyList()
)
