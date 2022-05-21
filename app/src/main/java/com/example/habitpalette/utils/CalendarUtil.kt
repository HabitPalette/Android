package com.example.habitpalette.utils

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import org.joda.time.DateTime
import org.joda.time.DateTimeConstants
import java.text.SimpleDateFormat
import java.util.*

class CalendarUtil {
    companion object {

        const val WEEKS_PER_MONTH = 6

        // 선택 날짜에 대한 monthly calendar return
        fun getMonthList(dateTime: DateTime): List<DateTime> {
            val list = mutableListOf<DateTime>()

            val date = dateTime.withDayOfMonth(1)
            val prev = getPrevOffSet(date)

            val startValue = date.minusDays(prev)

            val totalDay = DateTimeConstants.DAYS_PER_WEEK * WEEKS_PER_MONTH

            for (i in 0 until totalDay) {
                list.add(DateTime(startValue.plusDays(i)))
            }

            return list
        }

        // 해당 calendar의 이전 month의 day 수 return
        private fun getPrevOffSet(dateTime: DateTime): Int {
            var prevMonthTailOffset = dateTime.dayOfWeek

            if (prevMonthTailOffset >= 7) prevMonthTailOffset %= 7

            return prevMonthTailOffset
        }

        // 오늘 날짜인지 체크
        fun isToday(first: DateTime, second: DateTime): Boolean =
            first.year == second.year && first.monthOfYear == second.monthOfYear && first.dayOfMonth ==second.dayOfMonth

        // 같은 달인지 체크
        fun isSameMonth(first: DateTime, second: DateTime): Boolean =
            first.year == second.year && first.monthOfYear == second.monthOfYear

        // 일 -> 빨강, 토 -> 파랑, 나머지 -> 검은색
        @ColorInt
        fun getDateColor(@IntRange(from=1, to=7) dayOfWeek: Int): Int {
            return when (dayOfWeek) {
                DateTimeConstants.SATURDAY -> Color.parseColor("#2962FF")

                DateTimeConstants.SUNDAY -> Color.parseColor("#D32F2F")

                else -> Color.parseColor("#000000")
            }
        }
        @JvmStatic
        fun dateStringFromFormat(locale: Locale = Locale.getDefault(), date: Date, format: String): String? {
            return try {
                val formatter = SimpleDateFormat(format, locale)
                formatter.format(date)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}