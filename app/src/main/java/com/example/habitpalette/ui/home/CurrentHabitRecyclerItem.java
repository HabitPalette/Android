package com.example.habitpalette.ui.home;

import java.io.Serializable;
import java.util.Date;

public class CurrentHabitRecyclerItem {
    private String mHabitTitle;
    private Date mHabitStartDate;
    private float mHabitScore;
    private int mHabitColor;

    public CurrentHabitRecyclerItem(String mHabitTitle, Date mHabitStartDate, float mHabitScore, int mHabitColor) {
        this.mHabitTitle = mHabitTitle;
        this.mHabitStartDate = mHabitStartDate;
        this.mHabitScore = mHabitScore;
        this.mHabitColor = mHabitColor;
    }

    public void setHabitTitle(String title) {
        mHabitTitle = title;
    }

    public void setHabitPeriod(Date startDate) {
        mHabitStartDate = startDate;
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

    public Date getHabitPeriod() {
        return this.mHabitStartDate;
    }

    public float getHabitScore() {
        return this.mHabitScore;
    }

    public int getHabitColor() {
        return this.mHabitColor;
    }
}
