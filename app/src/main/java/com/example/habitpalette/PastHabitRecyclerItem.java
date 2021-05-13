package com.example.habitpalette;

import java.util.Date;

public class PastHabitRecyclerItem {
    private String mHabitTitle;
    private Date mHabitStartDate;
    private Date mHabitEndDate;
    private float mHabitScore;
    private int mHabitColor;

    public PastHabitRecyclerItem(String mHabitTitle, Date mHabitStartDate, Date mHabitEndDate, float mHabitScore, int mHabitColor) {
        this.mHabitTitle = mHabitTitle;
        this.mHabitStartDate = mHabitStartDate;
        this.mHabitEndDate = mHabitEndDate;
        this.mHabitScore = mHabitScore;
        this.mHabitColor = mHabitColor;
    }

    public void setHabitTitle(String title) {
        mHabitTitle = title;
    }

    public void setHabitStartDate(Date startDate) {
        mHabitStartDate = startDate;
    }

    public void setHabitEndDate(Date endDate) {
        mHabitEndDate = endDate;
    }

    public void setHabitScore(float score) {
        mHabitScore = score;
    }

    public void setHabitColor(int color) {
        mHabitColor = color;
    }

    public String getHabitTitle() {
        return this.mHabitTitle;
    }

    public Date getHabitStartDate() {
        return this.mHabitStartDate;
    }

    public Date getHabitEndDate() {
        return this.mHabitEndDate;
    }

    public float getHabitScore() {
        return this.mHabitScore;
    }

    public int getHabitColor() {
        return this.mHabitColor;
    }
}
