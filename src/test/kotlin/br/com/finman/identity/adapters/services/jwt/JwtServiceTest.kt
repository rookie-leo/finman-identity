package br.com.finman.identity.adapters.services.jwt

import br.com.finman.identity.utils.getDadosUsuarioDomain
import io.jsonwebtoken.Jwts
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import javax.crypto.SecretKey
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class JwtServiceTest {

    @Mock
    private lateinit var keyGenerator: SecretKeyGenerator

    private val expirationTime = 60L

    private lateinit var jwtService: JwtService

    private lateinit var secretKey: SecretKey

    @BeforeEach
    fun setup() {
        secretKey = Jwts.SIG.HS256.key().build()

        `when`(keyGenerator.getKey())
            .thenReturn(secretKey)

        jwtService = JwtService(
            keyGenerator,
            expirationTime
        )
    }


    @Test
    fun `should generate JWT token`() {
        val result = jwtService.generateAccessToken(getDadosUsuarioDomain())
        assertNotNull(result.value)
        assertEquals("Bearer", result.type)
        assertEquals(3600L, result.expiresInSeconds)
    }
}