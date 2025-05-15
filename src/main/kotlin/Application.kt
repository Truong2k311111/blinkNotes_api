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

    val rawJson = System.getenv("FIREBASE_CREDENTIALS")
        ?: error("FIREBASE_CREDENTIALS is not set")

    val serviceAccount = ByteArrayInputStream(rawJson.toByteArray())

    val option = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .build()

    FirebaseApp.initializeApp(option)
}
