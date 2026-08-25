package br.com.finman.identity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FinmanIdentityApplication

fun main(args: Array<String>) {
	runApplication<FinmanIdentityApplication>(*args)
}
