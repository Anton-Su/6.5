package Plugins

import io.ktor.http.ContentType
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureOpenApi() {
    routing {

        get("/swagger") {
            call.respondText(
                """
                <!DOCTYPE html>
                <html>
                <head>
                  <meta charset="utf-8" />
                  <meta name="viewport" content="width=device-width, initial-scale=1">
                  <title>Swagger UI</title>
                  <link rel="stylesheet" href="https://unpkg.com/swagger-ui-dist@4/swagger-ui.css" />
                </head>
                <body>
                  <div id="swagger-ui"></div>
                  <script src="https://unpkg.com/swagger-ui-dist@4/swagger-ui-bundle.js"></script>
                  <script>
                    window.onload = function() {
                      const ui = SwaggerUIBundle({ url: '/openapi.json', dom_id: '#swagger-ui' });
                    }
                  </script>
                </body>
                </html>
                """.trimIndent(), ContentType.Text.Html
            )
        }


        get("/redoc") {
            call.respondText(
                """
                <!DOCTYPE html>
                <html>
                <head>
                  <meta charset="utf-8" />
                  <meta name="viewport" content="width=device-width, initial-scale=1">
                  <title>ReDoc</title>
                </head>
                <body>
                  <redoc spec-url='/openapi.json'></redoc>
                  <script src="https://cdn.jsdelivr.net/npm/redoc@next/bundles/redoc.standalone.js"></script>
                </body>
                </html>
                """.trimIndent(), ContentType.Text.Html
            )
        }


        get("/openapi.json") {
            val json = """
            {
              "openapi": "3.0.0",
              "info": { "title": "Nobel API", "version": "1.0.0" },
              "paths": {
                "/prizes": {
                  "get": { "summary": "Get all prizes", "responses": { "200": { "description": "OK" } } }
                },
                "/prizes/{year}/{category}": {
                  "get": {
                    "summary": "Get prize by year and category",
                    "parameters": [
                      { "name": "year", "in": "path", "required": true, "schema": { "type": "integer" } },
                      { "name": "category", "in": "path", "required": true, "schema": { "type": "string" } }
                    ],
                    "responses": { "200": { "description": "OK" } }
                  }
                },
                "/prizes/{year}/{category}/laureates": {
                  "get": { "summary": "Get laureates for prize", "responses": { "200": { "description": "OK" } } }
                },
                "/auth/login": {
                  "post": {
                    "summary": "Login",
                    "requestBody": {
                      "content": { "application/json": { "schema": { "${'$'}ref": "#/components/schemas/LoginRequest" } } }
                    },
                    "responses": { "200": { "description": "OK" } }
                  }
                }
              },
              "components": {
                "schemas": {
                  "LoginRequest": {
                    "type": "object",
                    "properties": {
                      "username": { "type": "string" },
                      "password": { "type": "string" }
                    },
                    "required": [ "username", "password" ]
                  }
                }
              }
            }
            """.trimIndent()

            call.respondText(json, ContentType.Application.Json)
        }
    }
}





