package com.example.habitpalette.data.model

import java.util.*

data class HabitHistory(
    val id: Long,
    val habit_id: Long,
    val score: Long,
    val created_date: Date,
    val content: String,
    val day: String
    )