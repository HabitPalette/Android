package com.example.habitpalette.ui.calendar

import androidx.lifecycle.ViewModel
import com.example.habitpalette.data.model.TSLiveData
import java.util.*


class CalendarViewModel : ViewModel() {
    var mCalendar: TSLiveData<Calendar> = TSLiveData<Calendar>()
    fun setCalendar(calendar: Calendar?) {
        mCalendar.setValue(calendar)
    }
}