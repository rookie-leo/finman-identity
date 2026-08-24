package com.rookie_leo.pessoa.adapters.services.jwt

import com.rookie_leo.pessoa.core.domain.AccessToken
import com.rookie_leo.pessoa.core.domain.DadosUsuarioDomain
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.Date

@Service
class JwtService(
    private val keyGenerator: SecretKeyGenerator,

    @Value("\${security.jwt.expiration-minutes}")
    private val expirationTime: Long,
) {
    val expirationTimeInSeconds = expirationTime * 60

    fun generateAccessToken(dadosUsuarioDomain: DadosUsuarioDomain): AccessToken =
        AccessToken(
            Jwts.builder()
                .signWith(keyGenerator.getKey())
                .subject(dadosUsuarioDomain.pessoaId.toString())
                .claims(buildClaims(dadosUsuarioDomain))
                .expiration(generateExpirationDate())
                .compact(),
            expiresInSeconds = expirationTimeInSeconds
        )

    private fun generateExpirationDate(): Date =
        Date.from(
            Instant
                .now()
                .plusSeconds(expirationTimeInSeconds)
        )

    private fun buildClaims(dadosUsuarioDomain: DadosUsuarioDomain): Map<String, Any> =
        buildMap {
            put("email", dadosUsuarioDomain.email)
            put("nome", dadosUsuarioDomain.nome)
        }
}