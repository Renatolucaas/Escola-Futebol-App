package com.example.escolafutebolapp.service

import android.content.Context
import java.util.Calendar
import java.util.Properties
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class EmailService(private val context: Context) {

    private val props = Properties().apply {
        put("mail.smtp.auth", "true")
        put("mail.smtp.starttls.enable", "true")
        put("mail.smtp.host", getProperty("SMTP_SERVER", "smtp.gmail.com"))
        put("mail.smtp.port", getProperty("SMTP_PORT", "587"))
        put("mail.smtp.ssl.trust", getProperty("SMTP_SERVER", "smtp.gmail.com"))
    }

    private val username = getProperty("EMAIL_SENDER", "")
    private val password = getProperty("EMAIL_PASSWORD", "")

    private fun getProperty(key: String, defaultValue: String): String {
        return try {
            val properties = java.util.Properties()
            context.assets.open("local.properties").use { input ->
                properties.load(input)
            }
            properties.getProperty(key, defaultValue)
        } catch (e: Exception) {
            defaultValue
        }
    }

    private val session: Session by lazy {
        Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(username, password)
            }
        })
    }

    fun sendPasswordResetEmail(toEmail: String, resetToken: String) {
        try {
            val message = MimeMessage(session).apply {
                setFrom(InternetAddress(username, "Escola Futebol Jacareí"))
                setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail))
                subject = "Recuperação de Senha - Escola Futebol Jacareí"
                setContent(createEmailTemplate(resetToken), "text/html; charset=utf-8")
            }

            Thread {
                try {
                    Transport.send(message)
                    android.util.Log.d("EmailService", "Email enviado com sucesso para: $toEmail")
                } catch (e: Exception) {
                    android.util.Log.e("EmailService", "Erro ao enviar email: ${e.message}", e)
                    e.printStackTrace()
                }
            }.start()

        } catch (e: Exception) {
            android.util.Log.e("EmailService", "Erro ao preparar email: ${e.message}", e)
            throw Exception("Erro ao enviar email: ${e.message}")
        }
    }
