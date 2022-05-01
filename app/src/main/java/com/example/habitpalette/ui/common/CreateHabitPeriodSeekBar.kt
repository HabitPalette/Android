package com.example.habitpalette.ui.common

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import androidx.appcompat.widget.AppCompatSeekBar
import android.util.AttributeSet
import java.util.ArrayList
import androidx.annotation.AttrRes
import com.example.habitpalette.R
import kotlin.jvm.Synchronized

class CreateHabitPeriodSeekBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = R.attr.seekBarStyle
) : AppCompatSeekBar(context, attrs, defStyleAttr) {

    private var mRangeCount = 0
    private var mPaint = Paint()
    private var mProgressStrings: ArrayList<String>? = null

    init {
        mPaint = Paint().apply {
            color = Color.BLACK
            textSize = 44f
            isAntiAlias = true
            textAlign = Paint.Align.CENTER
        }
    }

    private fun dp2px(dp: Float): Int  = (context.resources.displayMetrics.density * dp + 0.5f).toInt()

    @SuppressLint("DrawAllocation")
    @Synchronized
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (width <= 0 || mRangeCount <= 0) {
            return
        }

        for (i in 0 until mRangeCount) {
            val bounds = Rect()
            val curText = mProgressStrings?.get(i)
            if (curText != null) {
                val textLength = curText.length
                mPaint.getTextBounds(curText, 0, textLength, bounds)
                val interval = (width - paddingLeft - paddingRight) * (i.toFloat() / max)
                val textY =
                    (height - paddingBottom - paddingTop) / 2.toFloat() + bounds.height() / 2.toFloat() - dp2px(
                        2f
                    )
                canvas.drawText(curText, interval, textY, mPaint)
            }
        }
    }

    fun setRangeCount(mRangeCount: Int) {
        this.mRangeCount = mRangeCount
        requestLayout()
    }

    fun setStrings(progressStrings: ArrayList<String>?) {
        mProgressStrings = progressStrings
        requestLayout()
    }
}