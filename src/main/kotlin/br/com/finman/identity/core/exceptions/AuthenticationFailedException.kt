package br.com.finman.identity.core.exceptions

class AuthenticationFailedException(
    message: String = "Usuario ou senha invalido"
): RuntimeException(message)