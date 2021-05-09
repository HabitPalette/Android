package com.example.habitpalette;

public class CurrentHabitRecyclerItem {
    private String mHabitTitle;
    private int mHabitPeriod;

    public CurrentHabitRecyclerItem(String mHabitTitle, int mHabitPeriod) {
        this.mHabitTitle = mHabitTitle;
        this.mHabitPeriod = mHabitPeriod;
    }

    public void setHabitTitle(String title) {
        mHabitTitle = title;
    }
    public void setHabitPeriod(int period) {
        mHabitPeriod = period;
    }

    public String getHabitTitle(){
        return this.mHabitTitle;
    }
    public int getHabitPeriod(){
        return this.mHabitPeriod;
    }
}
