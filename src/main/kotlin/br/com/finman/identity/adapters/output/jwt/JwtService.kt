package br.com.finman.identity.adapters.output.jwt

import br.com.finman.identity.adapters.configs.JwtProperties
import br.com.finman.identity.domain.AccessToken
import br.com.finman.identity.domain.AuthenticatedIdentity
import br.com.finman.identity.domain.exceptions.InvalidTokenException
import br.com.finman.identity.port.output.TokenService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import java.security.interfaces.RSAPublicKey
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
            .claim("name", identity.nome)
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

    override fun validate(token: String): AuthenticatedIdentity =
        try {
            val claims = Jwts.parser()
                .verifyWith(rsaKeyProvider.publicKey() as RSAPublicKey)
                .requireIssuer(jwtProperties.issuer)
                .requireAudience(jwtProperties.audience)
                .build()
                .parseSignedClaims(token)
                .payload

            claims.toAutenticatedIdentity()
        } catch (_: JwtException) {
            throw InvalidTokenException()
        } catch (_: IllegalArgumentException) {
            throw InvalidTokenException()
        }

    private fun Claims.toAutenticatedIdentity(): AuthenticatedIdentity {
        val roles = get("roles", List::class.java)
            ?.map { it as? String ?: throw InvalidTokenException() }
            ?.toSet()
            ?.takeIf { it.isNotEmpty() }
            ?: throw InvalidTokenException()

        val id = subject
            ?.let(UUID::fromString)
            ?: throw InvalidTokenException()

        val email = get("email", String::class.java)
            ?: throw InvalidTokenException()

        val name = get("name", String::class.java)
            ?: throw InvalidTokenException()

        if (issuedAt == null || expiration == null) throw InvalidTokenException()

        return AuthenticatedIdentity(
            id = id,
            email = email,
            nome = name,
            roles = roles
        )
    }
}
