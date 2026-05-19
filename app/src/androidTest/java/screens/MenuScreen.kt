package screens

class MenuScreen {package com.example.escolafutebolapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.escolafutebolapp.models.User
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    navController: NavController,
    currentUser: User?
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Menu Principal",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1A1A1A)
                ),
                actions = {
                    // ✅ Badge do tipo de usuário se existir
                    currentUser?.let { user ->
                        Badge(
                            containerColor = when (user.tipo_usuario) {
                                "admin" -> Color(0xFFDC2626)
                                "tecnico" -> Color(0xFF2563EB)
                                else -> Color(0xFF059669)
                            }
                        ) {
                            Text(
                                text = user.tipo_usuario.uppercase(),
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

}