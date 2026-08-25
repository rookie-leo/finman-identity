package br.com.finman.identity.port.output

interface PasswordHasher {
    fun hash(rawPassword: String): String
    fun matches(rawPassword: String, hashedPassword: String): Boolean
}
