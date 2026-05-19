import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.*

class `UserRepository.kt`(private val firestore: FirebaseFirestore) {

    suspend fun checkUserExists(email: String): Boolean {
        return try {
            val snapshot = firestore.collection("users")
                .whereEqualTo("email", email.toLowerCase())
                .limit(1)
                .get()
                .await()

            !snapshot.isEmpty
        } catch (e: Exception) {
            throw Exception("Erro ao verificar usuário: ${e.message}")
        }
    }

    suspend fun saveResetToken(email: String, token: String) {
        try {
            val resetData = hashMapOf(
                "resetToken" to token,
                "tokenExpiresAt" to Date(System.currentTimeMillis() + 15 * 60 * 1000), // 15 minutos
                "email" to email.toLowerCase()
            )

            firestore.collection("passwordResets")
                .document(email.toLowerCase())
                .set(resetData)
                .await()
        } catch (e: Exception) {
            throw Exception("Erro ao salvar token: ${e.message}")
        }
    }
