@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    application
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ktorPlugin)
    alias(libs.plugins.versionUpdate)
    alias(libs.plugins.catalogUpdate)
}

group = "com.nw"
version = "0.0.1"
application {
    mainClass.set("io.ktor.server.cio.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.bundles.ktorServer)
    implementation(libs.bundles.ktorClient)
    implementation(libs.bundles.ktorSerialization)
    implementation(libs.bundles.exposed)
    implementation(libs.bundles.dbConnector)
    implementation(libs.bundles.logging)
    implementation(libs.bundles.swaggerAndOpenAPI)
    implementation(libs.bundles.security)
    implementation(libs.bundles.cookies)
    implementation(libs.bundles.testing)
}
