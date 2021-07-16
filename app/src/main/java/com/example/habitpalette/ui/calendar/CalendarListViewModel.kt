package com.example.habitpalette.ui.calendar

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.habitpalette.utils.DateFormat
import com.example.habitpalette.utils.Keys
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class CalendarListViewModel(application: Application) : AndroidViewModel(application) {
    private val mCurrentTime = MutableLiveData<Long>()
    val mTitle = MutableLiveData<String>()
    val mCalendarList = MutableLiveData<MutableList<Any>>()
    var mCenterPosition :Int = 0

    fun initCalendarList(){
        val cal = GregorianCalendar()
        setCalendarList(cal)
    }

    fun setTitle(position: Int) {
        try {
            val item = mCalendarList.value?.get(position)
            if (item is Long) {
                setTitle(item)
            }
        } catch (e: Exception) {
            Log.e("error", e.toString())
        }
    }

    fun setTitle(time: Long) {
        mCurrentTime.value = time
        mTitle.setValue(DateFormat.getDate(time, DateFormat.CALENDAR_HEADER_FORMAT))
    }

    fun setCalendarList(cal: GregorianCalendar) {
        setTitle(cal.timeInMillis)
        var calendarList = ArrayList<Any>()
        for (i in -3 until 3) {
            try {
                val calendar = GregorianCalendar(
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH) + i,
                    1,
                    0,
                    0,
                    0
                )
                if (i == 0) {
                    mCenterPosition = calendarList.size
                }
                calendarList.add(calendar.timeInMillis)
                val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1
                val max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

                for (i in 0 until dayOfWeek) {
                    calendarList.add(Keys.EMPTY)
                }
                for (i in 1 until max + 1) {
                    calendarList.add(
                        GregorianCalendar(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            i
                        )
                    )
                }

            } catch (e: Exception) {
                Log.e("error", e.toString())
            }
            mCalendarList.value = calendarList
        }

    }

}