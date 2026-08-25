package br.com.finman.identity.adapters.services.impl

import br.com.finman.identity.adapters.services.PasswordHasher
import org.springframework.security.crypto.password.PasswordEncoder

class BCryptPasswordHasher(
    private val passwordEncoder: PasswordEncoder
): PasswordHasher {
    override fun encode(rawPassword: String): String =
        passwordEncoder.encode(rawPassword)!!

    override fun matches(rawPassword: String, encodePassword: String): Boolean =
        passwordEncoder.matches(rawPassword, encodePassword)
}