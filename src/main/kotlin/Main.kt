import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream


fun main() {

    val action = "action"
    val content = "content"

    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    val newLike = Message.builder()
        .putData(action, "LIKE")
        .putData(
            content, """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent()
        )
        .setToken(token)
        .build()

    val newPost = Message.builder()
        .putData(action, "NEW_POST")
        .putData(
            content, """{
          "userId": 1,
          "userName": "Android",
          "content": "New information"
        }""".trimIndent()
        )
        .setToken(token)
        .build()

    val update = Message.builder()
        .putData(action, "UPDATE")
        .putData(
            content, """{
          "userId": 1,
          "userName": "Vasiliy"
        }""".trimIndent()
        )
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(newLike)
    FirebaseMessaging.getInstance().send(newPost)
    FirebaseMessaging.getInstance().send(update)
}