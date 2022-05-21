package com.example.habitpalette.data.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(tableName = "habits")
data class Habit (
    @PrimaryKey(autoGenerate = true) var id : Long?,
    var title: String,
    val start_date: String,
    val end_date: String,
    val duration: Int,
    val is_completed: Boolean,
    var color: Int,
    val users_id: Long,
    var score: Float) {
    @RequiresApi(Build.VERSION_CODES.O)
    constructor() : this(null, "",LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")) , LocalDate.now().plusDays(10).format(DateTimeFormatter.ofPattern("yyyy.MM.dd")), 10,false, 0,0L, 0f)
}