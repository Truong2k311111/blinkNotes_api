
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
}

group = "com"
version = "0.0.1"

application {
    mainClass.set("com.ApplicationKt")
}
tasks {
    named<Jar>("jar") {
        manifest {
            attributes["Main-Class"] = "com.ApplicationKt"
        }
    }

    // Shadow jar nếu dùng plugin 'com.github.johnrengelman.shadow'
    register<JavaExec>("runJar") {
        group = "application"
        mainClass.set("com.ApplicationKt")
        classpath = sourceSets.main.get().runtimeClasspath
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)

    implementation("com.google.firebase:firebase-admin:9.2.0")

}
