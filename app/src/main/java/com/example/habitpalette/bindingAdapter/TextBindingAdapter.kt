package com.example.habitpalette.bindingAdapter

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.habitpalette.utils.DateFormat
import java.util.*

@BindingAdapter("setCalendarHeaderText")
fun setCalendarHeaderText(view: TextView, date: Long) {
    try {
        if (date != null) {
            view.setText(DateFormat.getDate(date, DateFormat.CALENDAR_HEADER_FORMAT))
        }
    } catch (e: Exception) {
        Log.e("error", e.toString())
    }
}

@BindingAdapter("setDayText")
fun setDayText(view: TextView, calendar: Calendar) {
    try {
        if (calendar != null) {
            var gregorianCalendar = GregorianCalendar(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                0,
                0,
                0
            )
            view.setText(
                DateFormat.getDate(
                    gregorianCalendar.timeInMillis,
                    DateFormat.DAY_FORMAT
                )
            )
        }
    } catch (e: Exception) {
        Log.e("error", e.toString())
    }
}

class TextBindingAdapter {

}
