package com.example.habitpalette.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "habits")
data class Habit (
    @PrimaryKey (autoGenerate = true) val id : Long,
    var title: String,
    val start_date: LocalDate,
    val end_date: LocalDate,
    val duration: Int,
    val is_completed: Boolean,
    val color: Int,
    val users_id: Long,
    val score: Float
        )