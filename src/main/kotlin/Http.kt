package com.example

import com.asyncapi.kotlinasyncapi.context.service.AsyncApiExtension
import com.asyncapi.kotlinasyncapi.ktor.AsyncApiPlugin
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import java.util.*

const val JWT_ISSUER = "com.example"
const val JWT_AUDIENCE = "com.example.audience"
const val JWT_REALM = "Ktor Sample App"
// Secret must be at least 32 chars for HMAC256
const val JWT_SECRET = "replace_with_a_long_secret_of_at_least_32_chars_!"

fun Application.configureHttp() {
    install(CallLogging)

    install(ContentNegotiation) {
        json(Json { prettyPrint = true; encodeDefaults = true })
    }

    install(Authentication) {
        jwt("auth-jwt") {
            realm = JWT_REALM
            verifier(
                JWT
                    .require(Algorithm.HMAC256(JWT_SECRET))
                    .withAudience(JWT_AUDIENCE)
                    .withIssuer(JWT_ISSUER)
                    .build()
            )
            validate { credential ->
                if (credential.payload.getClaim("username").asString() != null) JWTPrincipal(credential.payload) else null
            }
        }
    }

    // Keep AsyncAPI plugin for documentation if present
    install(AsyncApiPlugin) {
        extension = AsyncApiExtension.builder {
            info {
                title("Sample API")
                version("1.0.0")
            }
        }
    }
}
