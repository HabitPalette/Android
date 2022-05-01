package com.example.habitpalette.ui.common

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.animation.AccelerateDecelerateInterpolator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.habitpalette.R

class HabitScoreProgressView @JvmOverloads constructor(
    private val mContext: Context, attrs: AttributeSet?) : View(
    mContext, attrs
) {
    private var mPercent = 75f
    private var mStrokeWidth = 0f
    private var mBgColor = -0x1e1e1f
    private var mFgColorStart = -0x1c00
    private var mFgColorEnd = -0xb800
    private var mColorText = 0x000000
    private var mText: String? = "약속점수"
    private var mTextSize = 0f
    private var mShader: LinearGradient? = null
    private var mOval = RectF()
    private var mPaint = Paint()
    @SuppressLint("ObjectAnimatorBinding")
    private val animator = ObjectAnimator.ofFloat(this, "startAngle", 0f, 360f)

    init {
        val a =
            mContext.theme.obtainStyledAttributes(attrs, R.styleable.HabitScoreProgressView, 0, 0)
        try {
            mBgColor = a.getColor(R.styleable.HabitScoreProgressView_bgColor, -0x1e1e1f)
            mFgColorEnd = a.getColor(R.styleable.HabitScoreProgressView_fgColorEnd, -0xb800)
            mFgColorStart = a.getColor(R.styleable.HabitScoreProgressView_fgColorStart, -0x1c00)
            mPercent = a.getFloat(R.styleable.HabitScoreProgressView_percent, 75f)
            mStrokeWidth =
                a.getDimensionPixelSize(R.styleable.HabitScoreProgressView_strokeWidth, dp2px(21f))
                    .toFloat()
            mColorText = a.getColor(R.styleable.HabitScoreProgressView_textColor, 0x000000)
            mText = a.getString(R.styleable.HabitScoreProgressView_text)
            mTextSize =
                a.getDimensionPixelSize(R.styleable.HabitScoreProgressView_textSize, dp2px(10f))
                    .toFloat()
        } finally {
            a.recycle()
        }
        mPaint = Paint().apply {
            isAntiAlias = true
            strokeCap = Paint.Cap.ROUND
        }
        updateOval()
    }

    private fun dp2px(dp: Float)= (mContext.resources.displayMetrics.density * dp + 0.5f).toInt()

    @SuppressLint("ResourceAsColor")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint.strokeWidth = 1f
        mPaint.style = Paint.Style.FILL
        mPaint.textSize = 50f
        mPaint.color = mFgColorEnd
        mPaint.textAlign = Paint.Align.CENTER

        if (mText != null) {
            canvas.drawText(mText!!, 155f, 170f, mPaint)
        }

        mPaint.strokeWidth = mStrokeWidth
        mPaint.style = Paint.Style.STROKE
        mPaint.shader = null
        mPaint.color = mBgColor
        canvas.drawArc(mOval, 0f, 360f, false, mPaint)
        mPaint.shader = mShader
        canvas.drawArc(mOval, -70f, mPercent * 3.6f, false, mPaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateOval()
        mShader = LinearGradient(
            mOval.left, mOval.top,
            mOval.left, mOval.bottom, mFgColorStart, mFgColorEnd, Shader.TileMode.MIRROR
        )
    }

    private fun refreshTheLayout() {
        invalidate()
        requestLayout()
    }

    private fun updateOval() {
        val xp = paddingLeft + paddingRight
        val yp = paddingBottom + paddingTop
        mOval = RectF(
            paddingLeft + mStrokeWidth, paddingTop + mStrokeWidth,
            paddingLeft + (width - xp) - mStrokeWidth,
            paddingTop + (height - yp) - mStrokeWidth
        )
    }

    var percent: Float
        get() = mPercent
        set(mPercent) {
            this.mPercent = mPercent
            refreshTheLayout()
        }

    var text: String?
        get() = mText
        set(mText) {
            this.mText = mText
            refreshTheLayout()
        }

    var strokeWidth: Float
        get() = mStrokeWidth
        set(mStrokeWidth) {
            this.mStrokeWidth = mStrokeWidth
            mPaint.strokeWidth = mStrokeWidth
            updateOval()
            refreshTheLayout()
        }

    fun setStrokeWidthDp(dp: Float) {
        mStrokeWidth = dp2px(dp).toFloat()
        mPaint!!.strokeWidth = mStrokeWidth
        updateOval()
        refreshTheLayout()
    }

    var fgColorStart: Int
        get() = mFgColorStart
        set(mFgColorStart) {
            this.mFgColorStart = mFgColorStart
            if (mOval != null) {
                mShader = LinearGradient(
                    mOval.left, mOval.top,
                    mOval.left, mOval.bottom, mFgColorStart, mFgColorEnd, Shader.TileMode.MIRROR
                )
                refreshTheLayout()
            }
        }

    var fgColorEnd: Int
        get() = mFgColorEnd
        set(mFgColorEnd) {
            this.mFgColorEnd = mFgColorEnd
            mShader = LinearGradient(
                mOval.left, mOval.top,
                mOval.left, mOval.bottom, mFgColorStart, mFgColorEnd, Shader.TileMode.MIRROR
            )
            refreshTheLayout()
        }

    fun animateIndeterminate() {
        animateIndeterminate(800, AccelerateDecelerateInterpolator())
    }

    @SuppressLint("ObjectAnimatorBinding")
    fun animateIndeterminate(
        durationOneCircle: Int,
        interpolator: TimeInterpolator?
    ) {
        if (interpolator != null) animator.interpolator = interpolator
        animator.duration =durationOneCircle.toLong()
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.RESTART
        animator.start()
    }

    fun stopAnimateIndeterminate() {
        animator?.cancel()
    }
}