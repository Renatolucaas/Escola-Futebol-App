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


    /**
     * Método para verificar admin específico por email
     */
    suspend fun checkAdminExists(email: String): Boolean {
        return try {
            val admin = getUserByEmail(email)
            val exists = admin != null
            println("🔍 Verificando admin $email: ${if (exists) "EXISTE" else "NÃO EXISTE"}")
            exists
        } catch (e: Exception) {
            println("❌ Erro ao verificar admin $email: ${e.message}")
            false
        }
    }


    /**
     * ✅ FUNÇÃO NOVA: Busca o usuário atual com listener em tempo real
     */
    fun getCurrentUserRealtime(onUserChanged: (User?) -> Unit) {
        val firebaseUser = Firebase.auth.currentUser
        if (firebaseUser != null) {
            usersRef.child(firebaseUser.uid)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        try {
                            val user = snapshot.getValue(User::class.java)
                            if (user != null) {
                                println("🔄 Usuário atualizado em tempo real: ${user.nome}")
                            }
                            onUserChanged(user)
                        } catch (e: Exception) {
                            println("❌ Erro ao processar usuário em tempo real: ${e.message}")
                            onUserChanged(null)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        println("❌ Listener de usuário cancelado: ${error.message}")
                        onUserChanged(null)
                    }
                })
        } else {
            onUserChanged(null)
        }
    }

    /**
     * ✅ FUNÇÃO NOVA: Verifica se o usuário atual tem permissão de admin/tecnico
     */
    suspend fun currentUserCanEdit(): Boolean {
        return try {
            val currentUser = getCurrentUser()
            val canEdit = currentUser?.tipo_usuario == "admin" || currentUser?.tipo_usuario == "tecnico"
            println("🔐 Permissão de edição: ${if (canEdit) "PERMITIDO" else "NEGADO"} para ${currentUser?.nome}")
            canEdit
        } catch (e: Exception) {
            println("❌ Erro ao verificar permissões: ${e.message}")
            false
        }
    }
