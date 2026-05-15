package com.example.api

import com.example.JWT_AUDIENCE
import com.example.JWT_ISSUER
import com.example.JWT_REALM
import com.example.JWT_SECRET
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.toMap
import kotlinx.serialization.Serializable
import org.slf4j.LoggerFactory
import java.util.*

private val logger = LoggerFactory.getLogger("AuthRoutes")

@Serializable
data class LoginRequest(val username: String, val password: String)

@Serializable
data class TokenResponse(val token: String)

fun Route.authRoutes() {
    route("/auth") {
        post("/login") {
            logger.info("POST /auth/login received")
            logger.info("Content-Type: ${call.request.contentType()}")
            logger.info("Headers: ${call.request.headers.toMap()}")

            val req = try {
                call.receive<LoginRequest>()
            } catch (e: Exception) {
                logger.error("Failed to receive/parse LoginRequest", e)
                logger.error("Exception type: ${e::class.simpleName}")
                logger.error("Exception message: ${e.message}")
                call.respond(
                    HttpStatusCode.BadRequest,
                    "Invalid or missing JSON body. Expected {\"username\":\"...\", \"password\":\"...\"}. Error: ${e.message}"
                )
                return@post
            }

            logger.info("Parsed login request: username=${req.username}")

            // Simple demo auth: admin / password
            if (req.username == "admin" && req.password == "password") {
                val expiresAt = Date(System.currentTimeMillis() + 30 * 60 * 1000) // 30 minutes
                val token = JWT.create()
                    .withAudience(JWT_AUDIENCE)
                    .withIssuer(JWT_ISSUER)
                    .withClaim("username", req.username)
                    .withExpiresAt(expiresAt)
                    .sign(Algorithm.HMAC256(JWT_SECRET))
                logger.info("Login successful for user: ${req.username}")
                call.respond(TokenResponse(token))
            } else {
                logger.warn("Login failed: invalid credentials for username=${req.username}")
                call.respond(status = io.ktor.http.HttpStatusCode.Unauthorized, "Invalid credentials")
            }
        }
    }
}


