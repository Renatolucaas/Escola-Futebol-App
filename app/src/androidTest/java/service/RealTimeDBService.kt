package com.example.escolafutebolapp.service

import com.example.escolafutebolapp.models.User
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object RealtimeDBService {

    private val database = FirebaseDatabase.getInstance()
    private val usersRef = database.getReference("users")

    /**
     * ✅ FUNÇÃO NOVA: Busca o usuário atual logado no Firebase Auth
     */
    suspend fun getCurrentUser(): User? {
        return try {
            val firebaseUser = Firebase.auth.currentUser
            if (firebaseUser != null) {
                println("🔍 Buscando usuário atual no Realtime Database: ${firebaseUser.uid}")
                val user = getUser(firebaseUser.uid)
                if (user != null) {
                    println("✅ Usuário atual encontrado: ${user.nome} (${user.tipo_usuario})")
                } else {
                    println("⚠️ Usuário do Auth não encontrado no Realtime Database")
                }
                user
            } else {
                println("ℹ️ Nenhum usuário logado no Firebase Auth")
                null
            }
        } catch (e: Exception) {
            println("❌ Erro ao buscar usuário atual: ${e.message}")
            e.printStackTrace()
            null
        }
    }

