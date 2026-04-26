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
//
