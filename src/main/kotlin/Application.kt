package com

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import io.ktor.server.application.*
import java.io.ByteArrayInputStream
import java.util.Base64

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureMonitoring()
    configureRouting()

    val base64Credentials = System.getenv("FIREBASE_CREDENTIALS")
        ?: error("FIREBASE_CREDENTIALS is not set")

    val credentialsStream = ByteArrayInputStream(Base64.getDecoder().decode(base64Credentials))
    val option = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(credentialsStream))
        .build()

    FirebaseApp.initializeApp(option)
}
