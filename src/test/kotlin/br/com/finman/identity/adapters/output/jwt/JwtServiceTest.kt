package br.com.finman.identity.adapters.output.jwt

import br.com.finman.identity.domain.AuthenticatedIdentity
import io.jsonwebtoken.Jwts
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.UUID
import javax.crypto.SecretKey
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class JwtServiceTest {
    @Mock private lateinit var keyGenerator: SecretKeyGenerator
    private lateinit var jwtService: JwtService

    @BeforeEach
    fun setup() {
        val secretKey: SecretKey = Jwts.SIG.HS256.key().build()
        `when`(keyGenerator.getKey()).thenReturn(secretKey)
        jwtService = JwtService(keyGenerator, 60)
    }

    @Test
    fun `deve gerar JWT bearer com expiracao configurada`() {
        val result = jwtService.generate(AuthenticatedIdentity(UUID.randomUUID(), "teste@email.com", "Teste"))

        assertNotNull(result.value)
        assertEquals("Bearer", result.type)
        assertEquals(3600L, result.expiresInSeconds)
    }
}
