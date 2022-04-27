package com.example.habitpalette.ui.common

import android.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import androidx.appcompat.widget.AppCompatSeekBar
import android.os.Build
import android.util.AttributeSet
import java.util.ArrayList
import kotlin.jvm.Synchronized

class CreateHabitPeriodSeekBar(
    private var mContext: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int
) : AppCompatSeekBar(
    mContext, attrs, defStyleAttr
) {
    private var mRangeCount = 0
    private var mPaint: Paint? = null
    private var mProgressStrings: ArrayList<String>? = null

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.attr.seekBarStyle
    ) {
        mContext = context
        init()
    }

    private fun init() {
        mPaint = Paint()
        mPaint!!.color = Color.BLACK
        mPaint!!.textSize = 44f
        mPaint!!.isAntiAlias = true
        mPaint!!.textAlign = Paint.Align.CENTER

        //Api21 and above call, remove the background behind the slider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            splitTrack = false
        }
    }

    private fun dp2px(dp: Float): Int {
        return (mContext.resources.displayMetrics.density * dp + 0.5f).toInt()
    }

    @Synchronized
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (width <= 0 || mRangeCount <= 0) {
            return
        }
        for (i in 0 until mRangeCount) {
            val bounds = Rect()
            val curText = mProgressStrings!![i]
            if (curText != null) {
                val textLength = curText.length
                mPaint!!.getTextBounds(curText, 0, textLength, bounds)
                val interval = (width - paddingLeft - paddingRight) * (i.toFloat() / max)
                val textY =
                    (height - paddingBottom - paddingTop) / 2.toFloat() + bounds.height() / 2.toFloat() - dp2px(
                        2f
                    )
                canvas.drawText(curText, interval, textY, mPaint!!)
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

    init {
        init()
    }
}