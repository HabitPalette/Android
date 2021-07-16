package com.example.habitpalette.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class DateFormat {
    companion object{
        var CALENDAR_HEADER_FORMAT: String = "MM월"
        var DAY_FORMAT: String = "d"

        fun getDate(date: Long, pattern: String): String {
            try {
                var formatter = SimpleDateFormat(pattern, Locale.ENGLISH)
                var d = Date(date)
                return formatter.format(d).toUpperCase()
            } catch (e: Exception) {
                return " "
                Log.e("error", e.toString())
            }

        }

    }

}