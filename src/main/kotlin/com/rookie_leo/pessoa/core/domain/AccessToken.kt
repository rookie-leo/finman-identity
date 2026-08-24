package com.rookie_leo.pessoa.core.domain

data class AccessToken(
    val value: String,
    val type: String? = "Bearer",
    val expiresInSeconds: Long
)