package screens

class RegisterScreen { package com.example.escolafutebolapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.escolafutebolapp.R
import com.example.escolafutebolapp.models.User
import com.example.escolafutebolapp.service.RealtimeDBService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun RegisterScreen(navController: NavController) {
    // ✅ DETECTA O TAMANHO DA TELA
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    // ✅ CATEGORIAS DE DISPOSITIVOS
    val isSmallPhone = screenWidth < 360.dp
    val isNormalPhone = screenWidth >= 360.dp && screenWidth < 480.dp
    val isLargePhone = screenWidth >= 480.dp && screenWidth < 600.dp
    val isSmallTablet = screenWidth >= 600.dp && screenWidth < 800.dp
    val isLargeTablet = screenWidth >= 800.dp && screenWidth < 1024.dp
    val isXLargeTablet = screenWidth >= 1024.dp

    // ✅ PADDINGS HORIZONTAIS - AUMENTADOS
    val horizontalPadding = when {
        isXLargeTablet -> 160.dp
        isLargeTablet -> 120.dp
        isSmallTablet -> 80.dp
        isLargePhone -> 40.dp
        isNormalPhone -> 28.dp
        else -> 20.dp
    }

    // ✅ PADDINGS VERTICAIS - AUMENTADOS
    val verticalPadding = when {
        isXLargeTablet -> 32.dp
        isLargeTablet -> 28.dp
        isSmallTablet -> 24.dp
        isLargePhone -> 20.dp
        isNormalPhone -> 16.dp
        else -> 12.dp
    }

    // ✅ PADDING INTERNO DOS CARDS - AUMENTADOS
    val cardPadding = when {
        isXLargeTablet -> 64.dp
        isLargeTablet -> 56.dp
        isSmallTablet -> 48.dp
        isLargePhone -> 40.dp
        isNormalPhone -> 32.dp
        else -> 28.dp
    }

    // ✅ TAMANHO DO LOGO - AUMENTADO
    val logoSize = when {
        isXLargeTablet -> 120.dp
        isLargeTablet -> 100.dp
        isSmallTablet -> 85.dp
        isLargePhone -> 75.dp
        isNormalPhone -> 70.dp
        else -> 65.dp
    }

    // ✅ TAMANHO DOS ÍCONES - AUMENTADO
    val iconSize = when {
        isXLargeTablet -> 26.dp
        isLargeTablet -> 24.dp
        isSmallTablet -> 22.dp
        isLargePhone -> 20.dp
        isNormalPhone -> 19.dp
        else -> 18.dp
    }

    // ✅ FONTE DO TÍTULO - AUMENTADA
    val fontSizeTitle = when {
        isXLargeTablet -> 32.sp
        isLargeTablet -> 28.sp
        isSmallTablet -> 24.sp
        isLargePhone -> 22.sp
        isNormalPhone -> 20.sp
        else -> 18.sp
    }

    // ✅ FONTE DO CORPO - AUMENTADA
    val fontSizeBody = when {
        isXLargeTablet -> 18.sp
        isLargeTablet -> 17.sp
        isSmallTablet -> 16.sp
        isLargePhone -> 15.sp
        isNormalPhone -> 15.sp
        else -> 14.sp
    }

    // ✅ FONTE PEQUENA - AUMENTADA
    val fontSizeSmall = when {
        isXLargeTablet -> 15.sp
        isLargeTablet -> 14.sp
        isSmallTablet -> 14.sp
        isLargePhone -> 13.sp
        isNormalPhone -> 13.sp
        else -> 12.sp
    }

    // ✅ ALTURA DOS BOTÕES - AUMENTADA
    val buttonHeight = when {
        isXLargeTablet -> 72.dp
        isLargeTablet -> 68.dp
        isSmallTablet -> 64.dp
        isLargePhone -> 60.dp
        isNormalPhone -> 56.dp
        else -> 54.dp
    }

    // ✅ ALTURA DOS CAMPOS DE TEXTO - AUMENTADA
    val textFieldHeight = when {
        isXLargeTablet -> 72.dp
        isLargeTablet -> 68.dp
        isSmallTablet -> 64.dp
        isLargePhone -> 60.dp
        isNormalPhone -> 56.dp
        else -> 54.dp
    }

    // ✅ ESPAÇAMENTO ENTRE CAMPOS - AUMENTADO
    val spacingBetweenFields = when {
        isXLargeTablet -> 28.dp
        isLargeTablet -> 26.dp
        isSmallTablet -> 24.dp
        isLargePhone -> 22.dp
        isNormalPhone -> 20.dp
        else -> 18.dp

}

}

    // ✅ ESPAÇAMENTO ENTRE SEÇÕES - AUMENTADO
    val spacingBetweenSections = when {
        isXLargeTablet -> 36.dp
        isLargeTablet -> 32.dp
        isSmallTablet -> 28.dp
        isLargePhone -> 24.dp
        isNormalPhone -> 20.dp
        else -> 16.dp
    }

    // ✅ RAIO DOS CANTOS - AUMENTADO
    val cornerRadius = when {
        isXLargeTablet -> 28.dp
        isLargeTablet -> 26.dp
        isSmallTablet -> 24.dp
        isLargePhone -> 20.dp
        isNormalPhone -> 18.dp
        else -> 16.dp
    }

    // ✅ ELEVAÇÃO DAS SOMBRAS - AUMENTADA
    val shadowElevation = when {
        isXLargeTablet -> 12.dp
        isLargeTablet -> 11.dp
        isSmallTablet -> 10.dp
        else -> 8.dp
    }

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var successMessage by remember { mutableStateOf<String?>(null) }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    // Firebase Auth
    val auth: FirebaseAuth = Firebase.auth

    // Cores
    val darkBackground = Color(0xFF0D0D0D)
    val darkSurface = Color(0xFF1A1A1A)
    val accentRed = Color(0xFFE65C5C)
    val accentRedLight = Color(0xFFFF7B7B)
    val successGreen = Color(0xFF4CAF50)
    val white = Color(0xFFFFFFFF)
    val grayText = Color(0xFFB3B3B3)
    val grayDark = Color(0xFF404040)

    // Efeito para navegar após sucesso
    LaunchedEffect(successMessage) {
        if (successMessage != null) {
            // Aguarda 2 segundos e depois volta para o login
            kotlinx.coroutines.delay(2000)
            navController.popBackStack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Criar Conta",
                        color = white,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = fontSizeBody
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Voltar",
                            tint = white,
                            modifier = Modifier.size(iconSize)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = white
                )
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = darkBackground
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFF1A1A1A), darkBackground)
                        )
                    )
                    .padding(innerPadding)
            ) {
                val scrollState = rememberScrollState()

                // ✅ LAYOUT ADAPTATIVO
                if (screenWidth >= 600.dp) {
                    // ✅ TABLETS - CENTRALIZADO
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = horizontalPadding, vertical = verticalPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .verticalScroll(scrollState),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(spacingBetweenSections)
                        ) {
                            RegisterContent(
                                nome = nome,
                                email = email,
                                password = password,
                                confirmPassword = confirmPassword,
                                passwordVisible = passwordVisible,
                                confirmPasswordVisible = confirmPasswordVisible,
                                isLoading = isLoading,
                                errorMessage = errorMessage,
                                successMessage = successMessage,
                                onNomeChange = { nome = it },
                                onEmailChange = { email = it },
                                onPasswordChange = { password = it },
                                onConfirmPasswordChange = { confirmPassword = it },
                                onPasswordVisibleChange = { passwordVisible = !passwordVisible },
                                onConfirmPasswordVisibleChange = { confirmPasswordVisible = !confirmPasswordVisible },
                                onRegisterClick = {
                                    registerUser(
                                        nome = nome,
                                        email = email,
                                        password = password,
                                        confirmPassword = confirmPassword,
                                        auth = auth,
                                        onLoadingChange = { isLoading = it },
                                        onErrorMessageChange = { errorMessage = it },
                                        onSuccessMessageChange = { successMessage = it }
                                    )
                                },
                                onBackToLoginClick = { navController.popBackStack() },
                                cardPadding = cardPadding,
                                buttonHeight = buttonHeight,
                                textFieldHeight = textFieldHeight,
                                fontSizeTitle = fontSizeTitle,
                                fontSizeBody = fontSizeBody,
                                fontSizeSmall = fontSizeSmall,
                                iconSize = iconSize,
                                logoSize = logoSize,
                                darkSurface = darkSurface,
                                white = white,
                                grayText = grayText,
                                grayDark = grayDark,
                                accentRed = accentRed,
                                accentRedLight = accentRedLight,
                                successGreen = successGreen,
                                spacingBetweenFields = spacingBetweenFields,
                                cornerRadius = cornerRadius,
                                shadowElevation = shadowElevation
                            )
                        }
                    }
                } else {
