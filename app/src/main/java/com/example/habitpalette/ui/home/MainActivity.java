package com.example.habitpalette.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.habitpalette.ui.calendar.HabitCalendarActivity;
import com.example.habitpalette.ui.habit.CreateHabitActivity;
import com.example.habitpalette.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ArrayList<CurrentHabitRecyclerItem> mCurrentHabitList;
    private CurrentHabitRecyclerAdapter mCurrentHabitAdapter;
    private PastHabitRecyclerAdapter mPastHabitAdapter;
    private ArrayList<PastHabitRecyclerItem> mPastHabitList;
    private ImageButton mCreateHabitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCreateHabitButton = (ImageButton) findViewById(R.id.button_create_habit);

        mCreateHabitButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), HabitCalendarActivity.class);
            startActivity(intent);
        });

        RecyclerView mCurrentHabitRecyclerView = (RecyclerView) findViewById(R.id.recycler_current_habit);
        LinearLayoutManager mCurrentHabitLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mCurrentHabitRecyclerView.setLayoutManager(mCurrentHabitLinearLayoutManager);

        mCurrentHabitList = new ArrayList<>();

        mCurrentHabitAdapter = new CurrentHabitRecyclerAdapter(mCurrentHabitList);
        mCurrentHabitRecyclerView.setAdapter(mCurrentHabitAdapter);


        RecyclerView mPastHabitRecyclerView = (RecyclerView) findViewById(R.id.recycler_past_habit);
        LinearLayoutManager mPastHabitLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mPastHabitRecyclerView.setLayoutManager(mPastHabitLinearLayoutManager);

        mPastHabitList = new ArrayList<>();

        SimpleDateFormat newDtFormat = new SimpleDateFormat("yyyy-MM-dd");

        // String 타입을 Date 타입으로 변환
        Date formatDate = null;
        try {
            formatDate = newDtFormat.parse("200301");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        PastHabitRecyclerItem data11 = new PastHabitRecyclerItem("코어운동 홈트 30분", formatDate, formatDate, (float)4.2,0xffad599e );
        PastHabitRecyclerItem data12 = new PastHabitRecyclerItem("코어운동 홈트 30분", formatDate, formatDate, (float)4.2,0xffad599e );
        PastHabitRecyclerItem data13 = new PastHabitRecyclerItem("코어운동 홈트 30분", formatDate, formatDate, (float)4.2,0xffad599e );
        PastHabitRecyclerItem data14 = new PastHabitRecyclerItem("코어운동 홈트 30분", formatDate, formatDate, (float)4.2,0xffad599e );
        PastHabitRecyclerItem data15 = new PastHabitRecyclerItem("코어운동 홈트 30분", formatDate, formatDate, (float)4.2,0xffad599e );

        mPastHabitList.add(data11);
        mPastHabitList.add(data12);
        mPastHabitList.add(data13);
        mPastHabitList.add(data14);
        mPastHabitList.add(data15);


        mPastHabitAdapter = new PastHabitRecyclerAdapter(mPastHabitList);
        mPastHabitRecyclerView.setAdapter(mPastHabitAdapter);

    }
}