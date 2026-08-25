package com.rookie_leo.pessoa.adapters.services.impl

import com.rookie_leo.pessoa.adapters.services.PasswordHasher
import org.springframework.security.crypto.password.PasswordEncoder

class BCryptPasswordHasher(
    private val passwordEncoder: PasswordEncoder
): PasswordHasher {
    override fun encode(rawPassword: String): String =
        passwordEncoder.encode(rawPassword)!!

    override fun matches(rawPassword: String, encodePassword: String): Boolean =
        passwordEncoder.matches(rawPassword, encodePassword)
}