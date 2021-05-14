package com.example.habitpalette;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreateHabitActivity extends AppCompatActivity {
    private EditText mHabitName;
    private EditText mHabitStartDate;
    private RadioGroup mHabitColorGroup1;
    private RadioGroup mHabitColorGroup2;
    private int mSelectedColor;

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

    }

    RadioGroup.OnCheckedChangeListener colorSelected1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId!=-1) {
                mHabitColorGroup2.setOnCheckedChangeListener(null);
                mHabitColorGroup2.clearCheck();
                mHabitColorGroup2.setOnCheckedChangeListener(colorSelected2);
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
            }
        }
    };
}
