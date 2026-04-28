package com.example.escolafutebolapp.uitls

import android.app.Application
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.escolafutebolapp.service.RealtimeDBService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AppLifecycleObserver(private val application: Application) : DefaultLifecycleObserver {

    companion object {
        private const val TAG = "AppLifecycleObserver"
        private const val DEFAULT_ADMIN_EMAIL = "admin@escolafutebol.com"
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        createDefaultAdmin()
    }

    private fun createDefaultAdmin() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                Log.d(TAG, "🎯 Iniciando verificação do admin padrão...")

                // ✅ VERIFICA SE O ADMIN PADRÃO ESPECÍFICO EXISTE
                val adminExists = RealtimeDBService.checkAdminExists(DEFAULT_ADMIN_EMAIL)
                Log.d(TAG, "🔍 Admin padrão existe? $adminExists")

                // ✅ SÓ CRIA SE NÃO EXISTIR
                if (!adminExists) {
                    Log.d(TAG, "🚀 Criando admin padrão...")
                    RealtimeDBService.createDefaultAdminIfNotExists()
                    Log.d(TAG, "✅ Admin criado com sucesso")
                } else {
                    Log.d(TAG, "✅ Admin já existe. Nada a fazer.")
                }

            } catch (e: Exception) {
                Log.e(TAG, "❌ Erro ao verificar/criar admin: ${e.message}", e)
            }
        }
    }
}