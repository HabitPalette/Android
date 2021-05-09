package com.example.habitpalette;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<CurrentHabitRecyclerItem> mCurrentHabitList;
    private CurrentHabitRecyclerAdapter mCurrentHabitAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mCurrentHabitRecyclerView = (RecyclerView) findViewById(R.id.recycler_current_habit);
        LinearLayoutManager mCurrentHabitLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mCurrentHabitRecyclerView.setLayoutManager(mCurrentHabitLinearLayoutManager);

        mCurrentHabitList = new ArrayList<>();

        CurrentHabitRecyclerItem data = new CurrentHabitRecyclerItem("운동",2);
        CurrentHabitRecyclerItem data2 = new CurrentHabitRecyclerItem("코딩",2);

        mCurrentHabitList.add(data);
        mCurrentHabitList.add(data2);

        mCurrentHabitAdapter = new CurrentHabitRecyclerAdapter(mCurrentHabitList);
        mCurrentHabitRecyclerView.setAdapter(mCurrentHabitAdapter);


    }
}