package br.com.finman.identity.domain.exceptions

class InvalidTokenException : RuntimeException("Token inválido ou expirado") {
}