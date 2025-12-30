package com.rookie_leo.pessoa.core.exceptions

class DatabaseException(
    override val message: String,
    override val cause: Throwable?,
    val errorCode: String? = null
) : RuntimeException(message)