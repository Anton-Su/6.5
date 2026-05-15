plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(ktorLibs.plugins.ktor)
}

group = "com.example"
version = "1.0.0-SNAPSHOT"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

kotlin {
    jvmToolchain(21)
}
dependencies {
    implementation(ktorLibs.server.config.yaml)
    implementation(ktorLibs.server.core)
    implementation(ktorLibs.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.openfolder.kotlinAsyncapiKtor)
    // Ktor plugins: call logging, content negotiation and serialization, auth JWT
    implementation("io.ktor:ktor-server-call-logging:3.4.0")
    implementation("io.ktor:ktor-server-content-negotiation:3.4.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.4.0")
    implementation("io.ktor:ktor-server-auth:3.4.0")
    implementation("io.ktor:ktor-server-auth-jwt:3.4.0")
    // Serialization
    implementation(libs.kotlinx.serialization.json)
    // JWT library
    implementation("com.auth0:java-jwt:4.4.0")

    testImplementation(kotlin("test"))
    testImplementation(ktorLibs.server.testHost)
}
