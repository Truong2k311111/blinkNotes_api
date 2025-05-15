import com.google.firebase.messaging.FirebaseMessaging
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.response.*

fun Route.sendNotification() {
    route("/send") {
        post {
            try {
                val body = call.receiveNullable<SendMessageDto>() ?: kotlin.run {
                    call.respond(HttpStatusCode.BadRequest, "Invalid request body")
                    return@post
                }
                FirebaseMessaging.getInstance().send(body.toMessage())
                call.respond(HttpStatusCode.OK, "Notification sent successfully")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Error: ${e.message}")
            }
        }
    }
    route("/broadcast") {
        post {
            try {
                val body = call.receiveNullable<SendMessageDto>() ?: kotlin.run {
                    call.respond(HttpStatusCode.BadRequest, "Invalid request body")
                    return@post
                }
                FirebaseMessaging.getInstance().send(body.toMessage())
                call.respond(HttpStatusCode.OK, "Broadcast sent successfully")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Error: ${e.message}")
            }
        }
    }
}
