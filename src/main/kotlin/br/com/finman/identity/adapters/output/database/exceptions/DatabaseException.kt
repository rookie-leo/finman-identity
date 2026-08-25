package br.com.finman.identity.adapters.output.database.exceptions

class DatabaseException(
    override val message: String,
    val errorCode: String? = null
) : RuntimeException(message)