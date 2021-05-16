package com.example.habitpalette;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CreateHabitActivity extends AppCompatActivity {
    private EditText mHabitName;
    private EditText mHabitStartDate;
    private RadioGroup mHabitColorGroup1;
    private RadioGroup mHabitColorGroup2;
    private int mSelectedColor;
    private ArrayList<String> mHabitPeriodType;
    private CreateHabitPeriodSeekBar mHabitPeriod;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_habit);

        mHabitStartDate = (EditText) findViewById(R.id.edit_text_habit_start_date);
        mHabitColorGroup1 = (RadioGroup) findViewById(R.id.radio_group_habit_color_row1);
        mHabitColorGroup1.clearCheck();
        mHabitColorGroup1.setOnCheckedChangeListener(colorSelected1);
        mHabitColorGroup2 = (RadioGroup) findViewById(R.id.radio_group_habit_color_row2);
        mHabitColorGroup2.clearCheck();
        mHabitColorGroup2.setOnCheckedChangeListener(colorSelected2);

        mHabitPeriod = (CreateHabitPeriodSeekBar) findViewById(R.id.seek_bar_habit_period);
        mHabitPeriod.setRangeCount(4);
        mHabitPeriodType = new ArrayList<>();
        mHabitPeriodType.add("10일");
        mHabitPeriodType.add("30일");
        mHabitPeriodType.add("50일");
        mHabitPeriodType.add("100일");

        mHabitPeriod.setStrings(mHabitPeriodType);
    }


    RadioGroup.OnCheckedChangeListener colorSelected1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId!=-1) {
                mHabitColorGroup2.setOnCheckedChangeListener(null);
                mHabitColorGroup2.clearCheck();
                mHabitColorGroup2.setOnCheckedChangeListener(colorSelected2);
            }
            if(checkedId==R.id.button_habit_color_pink){
                mSelectedColor = R.color.pink;
               }
            else if(checkedId==R.id.button_habit_color_red){
                mSelectedColor = R.color.red;
            }
            else if(checkedId==R.id.button_habit_color_orange){
                mSelectedColor = R.color.orange;
            }
            else if(checkedId==R.id.button_habit_color_yellow){
                mSelectedColor = R.color.yellow;
            }
            else if(checkedId==R.id.button_habit_color_green){
                mSelectedColor = R.color.green;
            }
        }
    };

    RadioGroup.OnCheckedChangeListener colorSelected2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId!=-1) {
                mHabitColorGroup1.setOnCheckedChangeListener(null);
                mHabitColorGroup1.clearCheck();
                mHabitColorGroup1.setOnCheckedChangeListener(colorSelected1);
            }if(checkedId==R.id.button_habit_color_light_green_blue){
                mSelectedColor = R.color.light_green_blue;
            }
            else if(checkedId==R.id.button_habit_color_blue){
                mSelectedColor = R.color.blue;
            }
            else if(checkedId==R.id.button_habit_color_light_blue_purple){
                mSelectedColor = R.color.light_blue_purple;
            }
            else if(checkedId==R.id.button_habit_color_purple){
                mSelectedColor = R.color.purple;
            }
            else if(checkedId==R.id.button_habit_color_gray){
                mSelectedColor = R.color.gray;
            }
        }
    };
}
