package br.com.finman.identity.adapters.output.jwt

import br.com.finman.identity.adapters.configs.JwtProperties
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Path
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.Base64

@Component
class RsaKeyProvider(
    private val jwtProperties: JwtProperties
) {

    private val keyFactory = KeyFactory.getInstance("RSA")

    private val privateKey: PrivateKey by lazy {
        keyFactory.generatePrivate(
            PKCS8EncodedKeySpec(
                decodePem(jwtProperties.privateKeyPath)
            )
        )
    }

    private val publicKey: PublicKey by lazy {
        keyFactory.generatePublic(
            X509EncodedKeySpec(
                decodePem(jwtProperties.publicKeyPath)
            )
        )
    }

    fun privateKey(): PrivateKey = privateKey

    fun publicKey(): PublicKey = publicKey

    private fun decodePem(path: String): ByteArray {
        val content = Files.readString(Path.of(path))
            .replace(Regex("-----BEGIN [A-Z ]+-----"), "")
            .replace(Regex("-----END [A-Z ]+-----"), "")
            .replace(Regex("\\s"), "")

        return Base64.getDecoder().decode(content)
    }

}
