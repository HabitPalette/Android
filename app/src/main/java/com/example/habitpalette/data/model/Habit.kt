package com.example.habitpalette.data.model

import java.util.*

data class Habit (
    val id : Long,
    val title: String,
    val created_date: Date,
    val end_date: Date,
    val duration: Long,
    val is_completed: Boolean,
    val color: String,
    val users_id: Long
        )