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

}

    // ✅ DETECTA O TAMANHO DA TELA
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    // ✅ CALCULA VALORES RESPONSIVOS
    val isSmallScreen = screenWidth < 360.dp
    val isLargeScreen = screenWidth > 480.dp
    val isTablet = screenWidth > 600.dp
    val isLargeTablet = screenWidth > 800.dp

    // ✅ TAMANHOS RESPONSIVOS
    val horizontalPadding = when {
        isLargeTablet -> 120.dp
        isTablet -> 60.dp
        isLargeScreen -> 32.dp
        isSmallScreen -> 16.dp
        else -> 24.dp
    }

    val verticalPadding = when {
        isLargeTablet -> 60.dp
        isTablet -> 40.dp
        isSmallScreen -> 20.dp
        else -> 28.dp
    }

    val spacingBetweenSections = when {
        isLargeTablet -> 32.dp
        isTablet -> 28.dp
        isSmallScreen -> 20.dp
        else -> 24.dp
    }

    // ✅ Coroutine scope para logout
    val scope = rememberCoroutineScope()

    WelcomeContent(
        userName = decodedUserName,
        onTreinosClick = { navController.navigate("treino") },
        // ✅ CORREÇÃO: Passe o userId E userTipo na navegação
        onAgendaClick = {
            if (userId.isNotEmpty()) {
                navController.navigate("agenda/$userId/$userTipo")
                println("📍 Navegando para: agenda/$userId/$userTipo")
            } else {
                println("❌ Erro: userId não encontrado")
                // Fallback: navega para login se não tiver userId
                navController.navigate("login") {
                    popUpTo(0) { inclusive = true }
                }
            }
        },
        onSairClick = {
            scope.launch {
                try {
                    // ✅ DESLOGA DO FIREBASE AUTH
                    println("🔓 Fazendo logout do Firebase Auth...")
                    FirebaseAuth.getInstance().signOut()
                    println("✅ Logout realizado com sucesso!")

                    // ✅ NAVEGA PARA LOGIN E LIMPA O BACK STACK
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                } catch (e: Exception) {
                    println("❌ Erro ao fazer logout: ${e.message}")
                    e.printStackTrace()
                }
            }
        },
        horizontalPadding = horizontalPadding,
        verticalPadding = verticalPadding,
        spacingBetweenSections = spacingBetweenSections,
        isTablet = isTablet,
        isSmallScreen = isSmallScreen,
        isLargeTablet = isLargeTablet
    )
}

@Composable
fun WelcomeContent(
    userName: String,
    onTreinosClick: () -> Unit,
    onAgendaClick: () -> Unit,
    onSairClick: () -> Unit,
    horizontalPadding: Dp,
    verticalPadding: Dp,
    spacingBetweenSections: Dp,
    isTablet: Boolean,
    isSmallScreen: Boolean,
    isLargeTablet: Boolean
) {
    // Cores escuras personalizadas
    val darkBackground = Color(0xFF0D0D0D)
    val darkSurface = Color(0xFF1A1A1A)
    val white = Color(0xFFFFFFFF)
    val grayText = Color(0xFFB3B3B3)
    val mediumGray = Color(0xFF404040)

