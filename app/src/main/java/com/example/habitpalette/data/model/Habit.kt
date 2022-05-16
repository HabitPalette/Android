package com.example.habitpalette.data.model

import java.time.LocalDate
import java.util.*

data class Habit (
    val id : Long,
    val title: String,
    val start_date: LocalDate,
    val end_date: LocalDate,
    val duration: Int,
    val is_completed: Boolean,
    val color: Int,
    val users_id: Long,
    val score: Float
        )