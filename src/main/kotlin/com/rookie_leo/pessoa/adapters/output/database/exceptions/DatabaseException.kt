package com.rookie_leo.pessoa.adapters.output.database.exceptions

class DatabaseException(
    override val message: String,
    val errorCode: String? = null
) : RuntimeException(message)