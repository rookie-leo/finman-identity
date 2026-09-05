package br.com.finman.identity.adapters.configs

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.test.assertEquals

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = [
        "spring.datasource.url=jdbc:h2:mem:security-test;MODE=MySQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
        "security.jwt.secret=test-secret-key-with-at-least-thirty-two-characters",
        "app.cors.allowed-origins=http://localhost:5173"
    ]
)
class SecurityConfigIntegrationTest(
    @LocalServerPort private val port: Int
) {
    private val httpClient = HttpClient.newHttpClient()

    @Test
    fun `deve permitir que a requisicao de login alcance o controller`() {
        val response = request(
            path = "/auth/login",
            method = "POST",
            body = """{"email":"","senha":""}""",
            headers = mapOf(HttpHeaders.CONTENT_TYPE to MediaType.APPLICATION_JSON_VALUE)
        )

        assertEquals(400, response.statusCode())
    }

    @Test
    fun `deve rejeitar auth me sem token`() {
        val response = request(path = "/auth/me")

        assertEquals(401, response.statusCode())
    }

    @Test
    fun `deve rejeitar endpoints legados sem autenticacao`() {
        val response = request(path = "/pessoas")

        assertEquals(401, response.statusCode())
    }

    @Test
    fun `deve rejeitar rota sem regra explicita`() {
        val response = request(path = "/rota-nao-declarada")

        assertEquals(401, response.statusCode())
    }

    @Test
    fun `deve aceitar preflight de origem permitida`() {
        val response = request(
            path = "/auth/login",
            method = "OPTIONS",
            headers = mapOf(
                HttpHeaders.ORIGIN to "http://localhost:5173",
                HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD to "POST"
            )
        )

        assertEquals(200, response.statusCode())
        assertEquals(
            "http://localhost:5173",
            response.headers().firstValue(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN).orElse(null)
        )
    }

    @Test
    fun `deve rejeitar preflight de origem nao permitida`() {
        val response = request(
            path = "/auth/login",
            method = "OPTIONS",
            headers = mapOf(
                HttpHeaders.ORIGIN to "https://origem-nao-permitida.example",
                HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD to "POST"
            )
        )

        assertEquals(403, response.statusCode())
    }

    private fun request(
        path: String,
        method: String = "GET",
        body: String? = null,
        headers: Map<String, String> = emptyMap()
    ): HttpResponse<String> {
        val requestBuilder = HttpRequest.newBuilder()
            .uri(URI("http://localhost:$port$path"))

        headers.forEach(requestBuilder::header)

        val publisher = body?.let(HttpRequest.BodyPublishers::ofString)
            ?: HttpRequest.BodyPublishers.noBody()

        return httpClient.send(
            requestBuilder.method(method, publisher).build(),
            HttpResponse.BodyHandlers.ofString()
        )
    }
}
