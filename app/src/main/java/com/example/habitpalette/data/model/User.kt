package com.example.habitpalette.data.model

data class User(
    val id: Long,
    val email: String,
    val user_name: String,
    val morning_notification: Boolean,
    val night_notification: Boolean
)