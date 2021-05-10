package com.example.habitpalette;

public class CurrentHabitRecyclerItem {
    private String mHabitTitle;
    private int mHabitPeriod;
    private float mHabitScore;

    public CurrentHabitRecyclerItem(String mHabitTitle, int mHabitPeriod, float mhabitScore) {
        this.mHabitTitle = mHabitTitle;
        this.mHabitPeriod = mHabitPeriod;
        this.mHabitScore = mhabitScore;
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

    public String getHabitTitle() {
        return this.mHabitTitle;
    }

    public int getHabitPeriod() {
        return this.mHabitPeriod;
    }

    public float getHabitScore() {
        return this.mHabitScore;
    }
}
