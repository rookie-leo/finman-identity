package com.rookie_leo.pessoa.adapters.services

interface PasswordHasher {
    fun encode(rawPassword: String): String
    fun matches(rawPassword: String, encodePassword: String): Boolean
}