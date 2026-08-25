package br.com.finman.identity.adapters.output.security

import br.com.finman.identity.port.output.PasswordHasher
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class BCryptPasswordHasher(
    private val passwordEncoder: PasswordEncoder
) : PasswordHasher {
    override fun hash(rawPassword: String): String = requireNotNull(passwordEncoder.encode(rawPassword))

    override fun matches(rawPassword: String, hashedPassword: String): Boolean =
        passwordEncoder.matches(rawPassword, hashedPassword)
}
