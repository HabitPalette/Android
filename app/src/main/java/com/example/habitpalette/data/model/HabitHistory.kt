package com.example.habitpalette.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "habit_history")
data class HabitHistory(
    @PrimaryKey(autoGenerate = true) var id : Long?,
    val habit_id: Long,
    val score: Long,
    val created_date: Date,
    val content: String,
    val day: String
    )