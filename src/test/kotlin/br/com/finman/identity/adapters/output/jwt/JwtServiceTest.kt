package br.com.finman.identity.adapters.output.jwt

import br.com.finman.identity.adapters.configs.JwtProperties
import br.com.finman.identity.domain.AuthenticatedIdentity
import br.com.finman.identity.domain.exceptions.InvalidTokenException
import io.jsonwebtoken.Jwts
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPublicKey
import java.time.Instant
import java.util.Date
import java.util.UUID
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class JwtServiceTest {
    private val rsaKeyProvider = mock<RsaKeyProvider>()
    private val jwtProperties = JwtProperties(
        privateKeyPath = "unused-in-unit-test",
        publicKeyPath = "unused-in-unit-test",
        issuer = "finman-identity",
        audience = "finman-api",
        expirationMinutes = 60
    )

    private lateinit var keyPair: KeyPair
    private lateinit var jwtService: JwtService

    @BeforeEach
    fun setup() {
        keyPair = KeyPairGenerator.getInstance("RSA").apply {
            initialize(2048)
        }.generateKeyPair()

        whenever(rsaKeyProvider.privateKey()).thenReturn(keyPair.private)
        whenever(rsaKeyProvider.publicKey()).thenReturn(keyPair.public)
        jwtService = JwtService(rsaKeyProvider, jwtProperties)
    }

    @Test
    fun `deve gerar JWT RS256 com as claims obrigatorias`() {
        val identity = AuthenticatedIdentity(
            id = UUID.randomUUID(),
            email = "teste@finman.com",
            nome = "Teste"
        )

        val result = jwtService.generate(identity)

        val parsedToken = Jwts.parser()
            .verifyWith(keyPair.public as RSAPublicKey)
            .requireIssuer(jwtProperties.issuer)
            .requireAudience(jwtProperties.audience)
            .build()
            .parseSignedClaims(result.value)

        val claims = parsedToken.payload

        assertEquals("RS256", parsedToken.header.algorithm)
        assertEquals(identity.id.toString(), claims.subject)
        assertEquals(identity.email, claims["email", String::class.java])
        assertEquals(identity.nome, claims["name", String::class.java])
        assertEquals(listOf("USER"), claims["roles", List::class.java])
        assertEquals(jwtProperties.issuer, claims.issuer)
        assertNotNull(claims.issuedAt)
        assertNotNull(claims.expiration)
        assertTrue(claims.expiration.after(claims.issuedAt))
        assertEquals("Bearer", result.type)
        assertEquals(3600L, result.expiresInSeconds)
    }

    @Test
    fun `deve validar e reconstruir identidade de token valido`() {
        val identity = AuthenticatedIdentity(
            id = UUID.randomUUID(),
            email = "teste@finman.com",
            nome = "Teste",
            roles = setOf("USER", "ADMIN")
        )

        val validatedIdentity = jwtService.validate(jwtService.generate(identity).value)

        assertEquals(identity, validatedIdentity)
    }

    @Test
    fun `deve rejeitar token expirado`() {
        val token = signedToken(
            expiration = Instant.now().minusSeconds(60)
        )

        assertFailsWith<InvalidTokenException> {
            jwtService.validate(token)
        }
    }

    @Test
    fun `deve rejeitar token assinado por outra chave`() {
        val otherKeyPair = KeyPairGenerator.getInstance("RSA").apply {
            initialize(2048)
        }.generateKeyPair()
        val token = signedToken(keyPair = otherKeyPair)

        assertFailsWith<InvalidTokenException> {
            jwtService.validate(token)
        }
    }

    @Test
    fun `deve rejeitar token com issuer diferente`() {
        val token = signedToken(issuer = "another-identity")

        assertFailsWith<InvalidTokenException> {
            jwtService.validate(token)
        }
    }

    @Test
    fun `deve rejeitar token com audience diferente`() {
        val token = signedToken(audience = "another-api")

        assertFailsWith<InvalidTokenException> {
            jwtService.validate(token)
        }
    }

    private fun signedToken(
        keyPair: KeyPair = this.keyPair,
        issuer: String = jwtProperties.issuer,
        audience: String = jwtProperties.audience,
        expiration: Instant = Instant.now().plusSeconds(60)
    ): String {
        val now = Instant.now()

        return Jwts.builder()
            .issuer(issuer)
            .audience().add(audience).and()
            .subject(UUID.randomUUID().toString())
            .claim("email", "teste@finman.com")
            .claim("name", "Teste")
            .claim("roles", listOf("USER"))
            .issuedAt(Date.from(now.minusSeconds(60)))
            .expiration(Date.from(expiration))
            .signWith(keyPair.private, Jwts.SIG.RS256)
            .compact()
    }
}
