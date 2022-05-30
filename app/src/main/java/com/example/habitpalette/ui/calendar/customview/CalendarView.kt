package com.example.habitpalette.ui.calendar.customview

import android.app.AlertDialog
import android.content.Context
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.ViewGroup
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.children
import com.example.habitpalette.R
import com.example.habitpalette.data.model.SimpleEvent
import com.example.habitpalette.utils.CalendarUtil.Companion.WEEKS_PER_MONTH
import com.example.habitpalette.utils.CalendarUtil.Companion.dateStringFromFormat
import org.joda.time.DateTime
import org.joda.time.DateTimeConstants.DAYS_PER_WEEK
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.max

class CalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = R.attr.calendarViewStyle,
    @StyleRes defStyleRes: Int = R.style.Calendar_CalendarViewStyle
) : ViewGroup(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr){

    private var _height: Float = 0f

    interface OnDateSelected {
        fun onDateSelected(date: Date, event: SimpleEvent?)
    }

    // context, _height 초기화
    init {
        context.withStyledAttributes(attrs, R.styleable.CalendarView, defStyleAttr, defStyleRes) {
            _height = getDimension(R.styleable.CalendarView_dayHeight, 0f)
        }
    }

    // override 필수
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val h = paddingTop + paddingBottom + max(suggestedMinimumHeight, (_height * WEEKS_PER_MONTH).toInt())
        setMeasuredDimension(getDefaultSize(suggestedMinimumWidth, widthMeasureSpec), h)
    }

    // override 필수
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val iWidth = (width / DAYS_PER_WEEK).toFloat()
        val iHeight = (height / WEEKS_PER_MONTH).toFloat()

        var index = 0
        children.forEach { view ->
            val left = (index % DAYS_PER_WEEK) * iWidth
            val top = (index / DAYS_PER_WEEK) * iHeight
            view.layout(left.toInt(), top.toInt(), (left + iWidth).toInt(), (top + iHeight).toInt())
            index++
        }
    }

    // 달력 그리기 (firstDatOfMonth: 달의 시작 요일, list: 달력이 갖고 있는 요일과 이벤트 목록
    fun initCalendar(firstDayOfMonth: DateTime, list: List<DateTime>, eventMap: HashMap<Int, SimpleEvent>) {
        list.forEach {
            addView(DayItemView(
                context = context,
                date = it,
                firstDayOfMonth = firstDayOfMonth,
                eventMap = eventMap,
                dateSelectListener = object : OnDateSelected {
                    override fun onDateSelected(date: Date, event: SimpleEvent?) {
                        val selectedDate: String = dateStringFromFormat(date = date, format = "EEE, dd MMM yyyy") ?: ""
                        if (event != null) {
                            AlertDialog.Builder(context)
                                .setTitle("Event Clicked")
                                .setMessage(
                                    String.format(
                                        Locale.getDefault(),
                                        "Date: %s\n\nEvent: %s\n\nProgress: %s",
                                        selectedDate,
                                        event.title,
                                        event.progress
                                        )
                                    )
                                    .create()
                                    .show()
                            }
                        }
                    }
                )
            )
        }
    }
}