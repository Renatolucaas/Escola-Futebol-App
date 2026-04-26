package com.example.escolafutebolapp.models

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import com.google.firebase.database.PropertyName
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Evento(

    @get:PropertyName("id")
    @set:PropertyName("id")
    var id: String = "",
)
