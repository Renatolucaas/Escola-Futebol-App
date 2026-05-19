package screens

class WelcomeScreen {package com.example.escolafutebolapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.escolafutebolapp.R
import com.example.escolafutebolapp.ui.theme.EscolaFutebolAppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun WelcomeScreen(navController: NavController, userName: String = "Jogador") {
    // ✅ Decodifica o nome do usuário
    val decodedUserName = try {
        java.net.URLDecoder.decode(userName, "UTF-8")
    } catch (e: Exception) {
        userName
    }

    // ✅ Obtém o userId do usuário logado
    val currentUser = FirebaseAuth.getInstance().currentUser
    val userId = currentUser?.uid ?: ""

    // ✅ STATE PARA ARMAZENAR O TIPO DO USUÁRIO
    var userTipo by remember { mutableStateOf("aluno") }

    // ✅ BUSCA O TIPO DO USUÁRIO DO FIREBASE
    LaunchedEffect(userId) {
        if (userId.isNotEmpty()) {
            try {
                val database = Firebase.database("https://escola-de-futebol-jaca-default-rtdb.firebaseio.com/")
                val userRef = database.getReference("users/$userId/tipo_usuario")

                userRef.get().addOnSuccessListener { snapshot ->
                    userTipo = snapshot.getValue(String::class.java) ?: "aluno"
                    println("✅ Tipo de usuário obtido: $userTipo")
                }.addOnFailureListener {
                    println("❌ Erro ao obter tipo de usuário: ${it.message}")
                    userTipo = "aluno" // Fallback
                }
            } catch (e: Exception) {
                println("❌ Exceção ao obter tipo de usuário: ${e.message}")
                userTipo = "aluno" // Fallback
            }
        }

}