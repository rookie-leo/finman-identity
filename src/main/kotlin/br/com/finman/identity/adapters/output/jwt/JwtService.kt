package br.com.finman.identity.adapters.output.jwt

import br.com.finman.identity.domain.AccessToken
import br.com.finman.identity.domain.AuthenticatedIdentity
import br.com.finman.identity.port.output.TokenService
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.Date

@Service
class JwtService(
    private val keyGenerator: SecretKeyGenerator,
    @Value("\${security.jwt.expiration-minutes}") private val expirationTime: Long
) : TokenService {
    private val expirationTimeInSeconds = expirationTime * 60

    override fun generate(identity: AuthenticatedIdentity): AccessToken =
        AccessToken(
            value = Jwts.builder()
                .signWith(keyGenerator.getKey())
                .subject(identity.id.toString())
                .claims(mapOf("email" to identity.email, "nome" to identity.nome))
                .expiration(Date.from(Instant.now().plusSeconds(expirationTimeInSeconds)))
                .compact(),
            expiresInSeconds = expirationTimeInSeconds
        )
}
