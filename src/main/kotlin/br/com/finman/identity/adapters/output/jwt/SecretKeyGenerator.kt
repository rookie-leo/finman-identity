package br.com.finman.identity.adapters.output.jwt

import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.crypto.SecretKey

@Component
class SecretKeyGenerator(
    @Value("\${security.jwt.secret}") private val secret: String
) {
    private val key: SecretKey = Keys.hmacShaKeyFor(secret.toByteArray())

    fun getKey(): SecretKey = key
}
