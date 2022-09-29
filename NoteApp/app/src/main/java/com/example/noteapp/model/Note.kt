package com.example.noteapp.model

import android.icu.text.CaseMap
import java.time.LocalDateTime
import java.util.*

data class Note(
    val id : UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
)
