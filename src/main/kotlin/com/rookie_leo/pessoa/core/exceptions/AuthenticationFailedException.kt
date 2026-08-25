package com.rookie_leo.pessoa.core.exceptions

class AuthenticationFailedException(
    message: String = "Usuario ou senha invalido"
): RuntimeException(message)