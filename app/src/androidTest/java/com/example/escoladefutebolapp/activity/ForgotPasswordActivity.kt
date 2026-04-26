//package com.example.escolafutebolapp.ui.activity
//
//import UserRepository
//import android.graphics.Typeface
//import android.os.Bundle
//import android.text.InputType
//import android.view.Gravity
//import android.view.View
//import android.view.ViewGroup
//import android.widget.*
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.isVisible
//import androidx.lifecycle.ViewModelProvider
//import com.example.escolafutebolapp.R
//import com.example.escolafutebolapp.viewmodel.ForgotPasswordUiState
//import com.example.escolafutebolapp.viewmodel.ForgotPasswordViewModel
//import com.google.firebase.firestore.FirebaseFirestore

class ForgotPasswordActivity : AppCompatActivity() {
//
//    private lateinit var viewModel: ForgotPasswordViewModel
//
//    // Declaração dos componentes de UI
//    private lateinit var scrollView: ScrollView
//    private lateinit var mainLayout: LinearLayout
//    private lateinit var titleText: TextView
//    private lateinit var subtitleText: TextView
//    private lateinit var emailInput: EditText
//    private lateinit var emailLayout: LinearLayout
//    private lateinit var btnRequestReset: Button
//    private lateinit var tokenSection: LinearLayout
//    private lateinit var tokenInput: EditText
//    private lateinit var newPasswordInput: EditText
//    private lateinit var confirmPasswordInput: EditText
//    private lateinit var btnResetPassword: Button
//    private lateinit var btnBackToLogin: Button
//    private lateinit var progressBar: ProgressBar
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
////
////        // Cria a UI programaticamente
////        createUI()
////
////        // Configura o ViewModel
////        setupViewModel()
////
////        setupObservers()
////        setupClickListeners()
////    }
////
////    private fun createUI() {
////        // Configuração do ScrollView principal
////        scrollView = ScrollView(this).apply {
////            layoutParams = ViewGroup.LayoutParams(
////                ViewGroup.LayoutParams.MATCH_PARENT,
////                ViewGroup.LayoutParams.MATCH_PARENT
////            )
////            setPadding(50, 30, 50, 30)
////        }
////
////        // Layout principal
////        mainLayout = LinearLayout(this).apply {
////            layoutParams = ViewGroup.LayoutParams(
////                ViewGroup.LayoutParams.MATCH_PARENT,
////                ViewGroup.LayoutParams.WRAP_CONTENT
////            )
////            orientation = LinearLayout.VERTICAL
////        }
////
////        // Título
////        titleText = TextView(this).apply {
////            layoutParams = LinearLayout.LayoutParams(
////                LinearLayout.LayoutParams.MATCH_PARENT,
////                LinearLayout.LayoutParams.WRAP_CONTENT
////            ).apply {
////                gravity = Gravity.CENTER
////                setMargins(0, 0, 0, 20)
////            }
////            text = "Recuperar Senha"
////            textSize = 24f
////            setTypeface(null, Typeface.BOLD)
////            setTextColor(resources.getColor(android.R.color.black, null))
////        }
////
////        // Subtítulo
////        subtitleText = TextView(this).apply {
////            layoutParams = LinearLayout.LayoutParams(
////                LinearLayout.LayoutParams.MATCH_PARENT,
////                LinearLayout.LayoutParams.WRAP_CONTENT
////            ).apply {
////                gravity = Gravity.CENTER
////                setMargins(0, 0, 0, 50)
////            }
////            text = "Digite seu email para receber um código de verificação"
////            textSize = 14f
////            setTextColor(resources.getColor(android.R.color.darker_gray, null))
////            gravity = Gravity.CENTER
////        }
////
////        // Layout do email
////        emailLayout = LinearLayout(this).apply {
////            layoutParams = LinearLayout.LayoutParams(
////                LinearLayout.LayoutParams.MATCH_PARENT,
////                LinearLayout.LayoutParams.WRAP_CONTENT
////            ).apply {
////                setMargins(0, 0, 0, 30)
////            }
////            orientation = LinearLayout.VERTICAL
////        }
////
////        // Label do email
////        val emailLabel = TextView(this).apply {
////            layoutParams = LinearLayout.LayoutParams(
////                LinearLayout.LayoutParams.WRAP_CONTENT,
////                LinearLayout.LayoutParams.WRAP_CONTENT
////            ).apply {
////                setMargins(0, 0, 0, 10)
////            }
////            text = "Email"
////            textSize = 16f
////            setTextColor(resources.getColor(android.R.color.black, null))
////        }
////
