package com.example.habitpalette;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;

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
            Intent intent = new Intent(getApplicationContext(), CreateHabitActivity.class);
            startActivity(intent);
        });

        RecyclerView mCurrentHabitRecyclerView = (RecyclerView) findViewById(R.id.recycler_current_habit);
        LinearLayoutManager mCurrentHabitLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mCurrentHabitRecyclerView.setLayoutManager(mCurrentHabitLinearLayoutManager);

        mCurrentHabitList = new ArrayList<>();

        CurrentHabitRecyclerItem data1 = new CurrentHabitRecyclerItem("코딩",2, (float)5, 0xffad599e);
        CurrentHabitRecyclerItem data2 = new CurrentHabitRecyclerItem("코딩",10, (float)2.4, 0xffffd6e8);
        CurrentHabitRecyclerItem data3 = new CurrentHabitRecyclerItem("코딩",32, (float)3.6, 0xff5484ff);

        mCurrentHabitList.add(data1);
        mCurrentHabitList.add(data2);
        mCurrentHabitList.add(data3);

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