package br.com.finman.identity

import br.com.finman.identity.adapters.configs.JwtProperties
import br.com.finman.identity.adapters.output.jwt.RsaKeyProvider
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Files
import java.nio.file.Path
import java.security.KeyPairGenerator
import java.util.Base64
import kotlin.test.assertContentEquals

class RsaKeyProviderTest {

    @TempDir
    lateinit var temporaryDirectory: Path

    @Test
    fun `deve carregar chaves RSA em formato PEM e reutilizar as instancias`() {
        val keyPair = KeyPairGenerator.getInstance("RSA").apply {
            initialize(2048)
        }.generateKeyPair()
        val privateKeyPath = temporaryDirectory.resolve("private.pem")
        val publicKeyPath = temporaryDirectory.resolve("public.pem")

        Files.writeString(privateKeyPath, pem("PRIVATE KEY", keyPair.private.encoded))
        Files.writeString(publicKeyPath, pem("PUBLIC KEY", keyPair.public.encoded))

        val keyProvider = RsaKeyProvider(
            JwtProperties(
                privateKeyPath = privateKeyPath.toString(),
                publicKeyPath = publicKeyPath.toString(),
                issuer = "finman-identity",
                audience = "finman-api",
                expirationMinutes = 60
            )
        )

        assertContentEquals(keyPair.private.encoded, keyProvider.privateKey().encoded)
        assertContentEquals(keyPair.public.encoded, keyProvider.publicKey().encoded)
        kotlin.test.assertSame(keyProvider.privateKey(), keyProvider.privateKey())
        kotlin.test.assertSame(keyProvider.publicKey(), keyProvider.publicKey())
    }

    private fun pem(type: String, encoded: ByteArray): String = buildString {
        appendLine("-----BEGIN $type-----")
        appendLine(Base64.getMimeEncoder(64, "\n".toByteArray()).encodeToString(encoded))
        appendLine("-----END $type-----")
    }
}
