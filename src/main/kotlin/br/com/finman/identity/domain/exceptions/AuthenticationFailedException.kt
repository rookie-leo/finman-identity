package br.com.finman.identity.domain.exceptions

class AuthenticationFailedException(message: String = "Usuario ou senha invalido") : RuntimeException(message)
