package com.example.habitpalette.ui.calendar

import androidx.lifecycle.ViewModel
import com.example.habitpalette.data.model.TSLiveData


class CalendarHeaderViewModel : ViewModel() {
    var mHeaderDate = TSLiveData<Long>()
    fun setHeaderDate(headerDate: Long) {
        mHeaderDate.value = headerDate
    }
}