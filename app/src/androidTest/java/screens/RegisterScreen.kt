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