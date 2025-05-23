import com.google.firebase.messaging.Message
import com.google.firebase.messaging.Notification

@kotlinx.serialization.Serializable
data class SendMessageDto(
    val to: String?,
    val notification: NotificationBody
)
@kotlinx.serialization.Serializable
data class NotificationBody(
    val title: String,
    val body: String
)
fun SendMessageDto.toMessage(): Message {
    return Message.builder()
        .setNotification(
            Notification.builder()
                .setTitle(notification.title)
                .setBody(notification.body)
                .build()
        ).apply {
            if (to ==null){
                setTopic("chat")
            }else{
                setToken(to)
            }
        }
        .build()
}