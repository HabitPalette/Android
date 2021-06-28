package com.example.habitpalette.ui.common;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.Nullable;

import com.example.habitpalette.R;

public class HabitScoreProgressView extends View {

    private float mPercent = 75;
    private float mStrokeWidth;
    private int mBgColor = 0xffe1e1e1;
    private int mFgColorStart = 0xffffe400;
    private int mFgColorEnd = 0xffff4800;
    private int mColorText = 0x000000;
    private String mText = "약속점수";
    private float mTextSize;

    private LinearGradient mShader;
    private Context mContext;
    private RectF mOval;
    private Paint mPaint;

    private ObjectAnimator animator;

    public HabitScoreProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HabitScoreProgressView, 0, 0);

        try {
            mBgColor = a.getColor(R.styleable.HabitScoreProgressView_bgColor, 0xffe1e1e1);
            mFgColorEnd = a.getColor(R.styleable.HabitScoreProgressView_fgColorEnd, 0xffff4800);
            mFgColorStart = a.getColor(R.styleable.HabitScoreProgressView_fgColorStart, 0xffffe400);
            mPercent = a.getFloat(R.styleable.HabitScoreProgressView_percent, 75);
            mStrokeWidth = a.getDimensionPixelSize(R.styleable.HabitScoreProgressView_strokeWidth, dp2px(21));
            mColorText = a.getColor(R.styleable.HabitScoreProgressView_textColor, 0x000000);
            mText = a.getString(R.styleable.HabitScoreProgressView_text);
            mTextSize = a.getDimensionPixelSize(R.styleable.HabitScoreProgressView_textSize, dp2px(10));
        } finally {
            a.recycle();
        }
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private int dp2px(float dp) {
        return (int) (mContext.getResources().getDisplayMetrics().density * dp + 0.5f);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(50);
        mPaint.setColor(mFgColorEnd);
        mPaint.setTextAlign(Paint.Align.CENTER);
        if(mText!=null){
            canvas.drawText(mText, 155, 170, mPaint);
        }

        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setShader(null);
        mPaint.setColor(mBgColor);
        canvas.drawArc(mOval, 0, 360, false, mPaint);

        mPaint.setShader(mShader);
        canvas.drawArc(mOval, -70, mPercent * 3.6f, false, mPaint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        updateOval();

        mShader = new LinearGradient(mOval.left, mOval.top,
                mOval.left, mOval.bottom, mFgColorStart, mFgColorEnd, Shader.TileMode.MIRROR);
    }

    public float getPercent() {
        return mPercent;
    }

    public void setPercent(float mPercent) {
        this.mPercent = mPercent;
        refreshTheLayout();
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
        refreshTheLayout();
    }

    public float getStrokeWidth() {
        return mStrokeWidth;
    }

    public void setStrokeWidth(float mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
        mPaint.setStrokeWidth(mStrokeWidth);
        updateOval();
        refreshTheLayout();
    }

    private void updateOval() {
        int xp = getPaddingLeft() + getPaddingRight();
        int yp = getPaddingBottom() + getPaddingTop();
        mOval = new RectF(getPaddingLeft() + mStrokeWidth, getPaddingTop() + mStrokeWidth,
                getPaddingLeft() + (getWidth() - xp) - mStrokeWidth,
                getPaddingTop() + (getHeight() - yp) - mStrokeWidth);
    }

    public void setStrokeWidthDp(float dp) {
        this.mStrokeWidth = dp2px(dp);
        mPaint.setStrokeWidth(mStrokeWidth);
        updateOval();
        refreshTheLayout();
    }

    public void refreshTheLayout() {
        invalidate();
        requestLayout();
    }

    public int getFgColorStart() {
        return mFgColorStart;
    }

    public void setFgColorStart(int mFgColorStart) {
        this.mFgColorStart = mFgColorStart;
        if (mOval != null) {
            mShader = new LinearGradient(mOval.left, mOval.top,
                    mOval.left, mOval.bottom, mFgColorStart, mFgColorEnd, Shader.TileMode.MIRROR);
            refreshTheLayout();
        }
    }

    public int getFgColorEnd() {
        return mFgColorEnd;
    }

    public void setFgColorEnd(int mFgColorEnd) {
        this.mFgColorEnd = mFgColorEnd;
        if (mOval != null) {
            mShader = new LinearGradient(mOval.left, mOval.top,
                    mOval.left, mOval.bottom, mFgColorStart, mFgColorEnd, Shader.TileMode.MIRROR);
            refreshTheLayout();
        }
    }

    public void animateIndeterminate() {
        animateIndeterminate(800, new AccelerateDecelerateInterpolator());
    }

    @SuppressLint("ObjectAnimatorBinding")
    public void animateIndeterminate(int durationOneCircle,
                                     TimeInterpolator interpolator) {
        animator = ObjectAnimator.ofFloat(this, "startAngle", 0, 360);
        if (interpolator != null) animator.setInterpolator(interpolator);
        animator.setDuration(durationOneCircle);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();
    }

    public void stopAnimateIndeterminate() {
        if (animator != null) {
            animator.cancel();
            animator = null;
        }
    }

}
