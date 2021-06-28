package com.example.habitpalette.ui.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;

import java.util.ArrayList;

public class CreateHabitPeriodSeekBar extends androidx.appcompat.widget.AppCompatSeekBar {
    private int mRangeCount;
    private Paint mPaint;
    private Context mContext;
    private ArrayList<String> mProgressStrings;

    public CreateHabitPeriodSeekBar(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.seekBarStyle);
        this.mContext = context;
        init();
    }

    public CreateHabitPeriodSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(44);
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);

        //Api21 and above call, remove the background behind the slider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSplitTrack(false);
        }
    }

    private int dp2px(float dp) {
        return (int) (mContext.getResources().getDisplayMetrics().density * dp + 0.5f);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (getWidth() <= 0 || mRangeCount <= 0) {
            return;
        }
        for (int i = 0; i < mRangeCount; i++) {
            Rect bounds = new Rect();
            String curText = mProgressStrings.get(i);
            if (curText != null) {
                int textLength = curText.length();
                mPaint.getTextBounds(curText, 0, textLength, bounds);
                float interval = (getWidth() - getPaddingLeft() - getPaddingRight()) * ((float) i / getMax());
                float textX = interval;
                float textY = (getHeight()-getPaddingBottom()-getPaddingTop())/(float)2 + bounds.height()/(float)2-dp2px(2);
                canvas.drawText(curText, textX, textY, mPaint);
            }
        }
    }

    public void setRangeCount(int mRangeCount) {
        this.mRangeCount = mRangeCount;
        requestLayout();
    }

    public void setStrings(ArrayList<String> progressStrings) {
        this.mProgressStrings = progressStrings;
        requestLayout();
    }

}
