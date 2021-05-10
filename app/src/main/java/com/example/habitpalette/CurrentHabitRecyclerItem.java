package com.example.habitpalette;

public class CurrentHabitRecyclerItem {
    private String mHabitTitle;
    private int mHabitPeriod;
    private float mHabitScore;
    private int mHabitColor;

    public CurrentHabitRecyclerItem(String mHabitTitle, int mHabitPeriod, float mHabitScore, int mHabitColor) {
        this.mHabitTitle = mHabitTitle;
        this.mHabitPeriod = mHabitPeriod;
        this.mHabitScore = mHabitScore;
        this.mHabitColor = mHabitColor;
    }

    public void setHabitTitle(String title) {
        mHabitTitle = title;
    }

    public void setHabitPeriod(int period) {
        mHabitPeriod = period;
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

    public int getHabitPeriod() {
        return this.mHabitPeriod;
    }

    public float getHabitScore() {
        return this.mHabitScore;
    }

    public int getHabitColor() {
        return this.mHabitColor;
    }
}
