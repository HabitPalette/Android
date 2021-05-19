package com.example.habitpalette;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UpdateHabitActivity extends AppCompatActivity {
    private ImageButton mImageButtonGoCalendar;
    private EditText mEditTextName;
    private TextView mTextViewStartDate;
    private RadioGroup mRadioGroupColor1;
    private RadioGroup mRadioGroupColor2;
    private TextView mTextViewPeriod;
    private Button mButtonUpdate;
    private String mHabitName;
    private Date mHabitStartDate;
    private int mHabitPeriod = 0;
    private int mHabitColor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_habit);

        mEditTextName = (EditText) findViewById(R.id.edit_text_habit_name);
        mTextViewStartDate = (TextView) findViewById(R.id.text_view_habit_start_date);
        mTextViewPeriod = (TextView) findViewById(R.id.text_view_habit_period);
        mImageButtonGoCalendar = (ImageButton) findViewById(R.id.button_back_to_Calendar);
        mRadioGroupColor1 = (RadioGroup) findViewById(R.id.radio_group_habit_color_row1);
        mRadioGroupColor2 = (RadioGroup) findViewById(R.id.radio_group_habit_color_row2);
        mButtonUpdate = (Button) findViewById(R.id.button_habit_update);

        //name, start date, period, color 다 받아와서 그걸로 default 세팅하기

        mImageButtonGoCalendar.setOnClickListener(view -> {
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            intent.setFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
//            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
        });

        mEditTextName.addTextChangedListener(textWatcher);

        mHabitColor = R.color.pink;
        mRadioGroupColor1.setOnCheckedChangeListener(colorSelected1);
        mRadioGroupColor2.clearCheck();
        mRadioGroupColor2.setOnCheckedChangeListener(colorSelected2);

        mButtonUpdate.setOnClickListener(updateHabit);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mButtonUpdate.isEnabled()) {
            mButtonUpdate.setTextColor(getResources().getColor(R.color.white));
            mButtonUpdate.requestLayout();
        } else {
            mButtonUpdate.setTextColor(getResources().getColor(R.color.black));
            mButtonUpdate.requestLayout();
        }
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String name = mEditTextName.getText().toString().trim();
            mButtonUpdate.setEnabled(!(name.length() == 0));
        }

        public void afterTextChanged(Editable s) {
        }
    };

    View.OnClickListener updateHabit = v -> {
        mHabitName = mEditTextName.getText().toString();

        Toast.makeText(UpdateHabitActivity.this, mHabitName + mHabitStartDate + " " + Integer.toString(mHabitColor) + " " + Integer.toString(mHabitPeriod), Toast.LENGTH_SHORT).show();
    };

    RadioGroup.OnCheckedChangeListener colorSelected1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                mRadioGroupColor2.setOnCheckedChangeListener(null);
                mRadioGroupColor2.clearCheck();
                mRadioGroupColor2.setOnCheckedChangeListener(colorSelected2);
            }
            if (checkedId == R.id.button_habit_color_pink) {
                mHabitColor = R.color.pink;
            } else if (checkedId == R.id.button_habit_color_red) {
                mHabitColor = R.color.red;
            } else if (checkedId == R.id.button_habit_color_orange) {
                mHabitColor = R.color.orange;
            } else if (checkedId == R.id.button_habit_color_yellow) {
                mHabitColor = R.color.yellow;
            } else if (checkedId == R.id.button_habit_color_green) {
                mHabitColor = R.color.green;
            }
        }
    };

    RadioGroup.OnCheckedChangeListener colorSelected2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                mRadioGroupColor1.setOnCheckedChangeListener(null);
                mRadioGroupColor1.clearCheck();
                mRadioGroupColor1.setOnCheckedChangeListener(colorSelected1);
            }
            if (checkedId == R.id.button_habit_color_light_green_blue) {
                mHabitColor = R.color.light_green_blue;
            } else if (checkedId == R.id.button_habit_color_blue) {
                mHabitColor = R.color.blue;
            } else if (checkedId == R.id.button_habit_color_light_blue_purple) {
                mHabitColor = R.color.light_blue_purple;
            } else if (checkedId == R.id.button_habit_color_purple) {
                mHabitColor = R.color.purple;
            } else if (checkedId == R.id.button_habit_color_gray) {
                mHabitColor = R.color.gray;
            }
        }
    };
}
