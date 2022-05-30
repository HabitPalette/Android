package com.example.habitpalette.ui.calendar.customview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import android.util.AttributeSet
import android.view.ContextThemeWrapper
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.example.habitpalette.R
import com.example.habitpalette.data.model.SimpleEvent
import com.example.habitpalette.utils.CalendarUtil.Companion.dateStringFromFormat
import com.example.habitpalette.utils.CalendarUtil.Companion.getDateColor
import com.example.habitpalette.utils.CalendarUtil.Companion.isSameMonth
import com.example.habitpalette.utils.CalendarUtil.Companion.isToday
import org.joda.time.DateTime

@SuppressLint("ViewConstructor")
class DayItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes private val defStyleAttr: Int = R.attr.itemViewStyle,
    @StyleRes private val defStyleRes: Int = R.style.Calendar_ItemViewStyle,
    private val date: DateTime = DateTime(),
    private val firstDayOfMonth: DateTime = DateTime(),
    private val eventMap: HashMap<Int, SimpleEvent>,
    private val dateSelectListener: CalendarView.OnDateSelected
) : View(ContextThemeWrapper(context, defStyleRes), attrs, defStyleAttr) {

    private val bounds = Rect()
    private var textPaint: Paint = Paint()
    private var arcPaint: Paint = Paint()
    private val arcStrokeWidth = 12f.toDp

    init{
        context.withStyledAttributes(attrs, R.styleable.CalendarView, defStyleAttr, defStyleRes) {
            val dayTextSize = getDimensionPixelSize(R.styleable.CalendarView_dayTextSize, 0).toFloat()

            // textPaint 설정 (흰색 배경, 글씨는 빨강, 파랑, 검은색 중 1)
            textPaint = TextPaint().apply {
                isAntiAlias = true
                textSize = dayTextSize
                textAlign = Paint.Align.CENTER
                color = getDateColor(date.dayOfWeek)

                if (!isSameMonth(date, firstDayOfMonth)) {
                    alpha = 0
                }
            }

            val dateInt = (dateStringFromFormat(date = date.toDate(), format = "yyyyMMdd") ?: "0").toInt()

            // arcPaint 설정
            arcPaint = Paint().apply {
                strokeWidth = arcStrokeWidth
                color = ContextCompat.getColor(context, R.color.light_blue_purple)
                if (!isSameMonth(date, firstDayOfMonth)) {
                    alpha = 0
                }
                else if(isToday(date, DateTime.now())){
                    style = Paint.Style.STROKE
                    alpha = 100
                }
                else if (eventMap.contains(dateInt)) {
                    style = Paint.Style.FILL_AND_STROKE
                    alpha = 30
                }
                else{
                    alpha = 0
                }
            }
        }

        this.setOnClickListener {
            val key =(dateStringFromFormat(date = date.toDate(), format = "yyyyMMdd") ?: "0").toInt()

            if(!eventMap.containsKey(key)){
                eventMap[key] = SimpleEvent(
                    date = date.toDate(),
                    title = "Event New",
                    color = ContextCompat.getColor(context, R.color.light_blue_purple),
                    progress = 100
                )
            }
            dateSelectListener.onDateSelected(date.toDate(),eventMap[key])
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if(canvas==null) return

        // date 초기화
        val date = date.dayOfMonth.toString()

        // getTextBounds and set xPos, yPos center
        textPaint.getTextBounds(date, 0, date.length, bounds)
        val xPos = width / 2
        val yPos = (height / 2 - (textPaint.descent() + textPaint.ascent()) / 2).toInt()

        // canvas에 drawText
        canvas.drawText(date, xPos.toFloat(), yPos.toFloat(), textPaint);

        // set cx, cy center
        val cx = width / 2
        val cy = height / 2
        val radius = 56f

        // canvas에 drawArc
        canvas.drawCircle(cx.toFloat(), cy.toFloat(), radius, arcPaint)
    }
    private val Float.toDp get() = this / Resources.getSystem().displayMetrics.density
}