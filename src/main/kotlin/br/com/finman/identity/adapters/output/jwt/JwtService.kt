package br.com.finman.identity.adapters.output.jwt

import br.com.finman.identity.adapters.configs.JwtProperties
import br.com.finman.identity.domain.AccessToken
import br.com.finman.identity.domain.AuthenticatedIdentity
import br.com.finman.identity.port.output.TokenService
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class JwtService(
    private val rsaKeyProvider: RsaKeyProvider,
    private val jwtProperties: JwtProperties
) : TokenService {

    override fun generate(identity: AuthenticatedIdentity): AccessToken {
        val now = Instant.now()
        val expiresAt = now.plusSeconds(jwtProperties.expirationMinutes * 60)

        val token = Jwts.builder()
            .issuer(jwtProperties.issuer)
            .audience().add(jwtProperties.audience).and()
            .subject(identity.id.toString())
            .claim("email", identity.email)
            .claim("roles", identity.roles.toList())
            .issuedAt(Date.from(now))
            .expiration(Date.from(expiresAt))
            .signWith(rsaKeyProvider.privateKey(), Jwts.SIG.RS256)
            .compact()

        return AccessToken(
            value = token,
            expiresInSeconds = jwtProperties.expirationMinutes * 60
        )

    }
}
