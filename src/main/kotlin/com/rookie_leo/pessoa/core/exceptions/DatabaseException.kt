package com.rookie_leo.pessoa.core.exceptions

class DatabaseException(
    override val message: String,
    val errorCode: String? = null
) : RuntimeException(message)