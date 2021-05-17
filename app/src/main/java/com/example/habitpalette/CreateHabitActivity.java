package com.example.habitpalette;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateHabitActivity extends AppCompatActivity {
    private EditText mEditTextName;
    private EditText mEditTextStartDate;
    private RadioGroup mRadioGroupColor1;
    private RadioGroup mRadioGroupColor2;
    private CreateHabitPeriodSeekBar mSeekBarPeriod;
    private Button mButtonCreate;
    private String mHabitName;
    private Date mHabitStartDate;
    private int mHabitPeriod = 0;
    private int mHabitColor;
    private ArrayList<String> mArrayListPeriodType;
    private Date mCurrentDate;
    private CurrentHabitRecyclerItem mNewHabitItem;

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener myDatePicker = (view, year, month, dayOfMonth) -> {
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, month);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        try {
            updateLabel();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_habit);

        mEditTextName = (EditText) findViewById(R.id.edit_text_habit_name);
        mEditTextStartDate = (EditText) findViewById(R.id.edit_text_habit_start_date);
        mSeekBarPeriod = (CreateHabitPeriodSeekBar) findViewById(R.id.seek_bar_habit_period);
        mRadioGroupColor1 = (RadioGroup) findViewById(R.id.radio_group_habit_color_row1);
        mRadioGroupColor2 = (RadioGroup) findViewById(R.id.radio_group_habit_color_row2);
        mButtonCreate = (Button) findViewById(R.id.button_habit_create);

        mEditTextName.addTextChangedListener(textWatcher);

        mEditTextStartDate.setOnClickListener(v -> new DatePickerDialog(CreateHabitActivity.this, myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show());
        mEditTextStartDate.setInputType(InputType.TYPE_NULL);
        mEditTextStartDate.addTextChangedListener(textWatcher);

        mHabitColor = R.color.pink;
        mRadioGroupColor1.setOnCheckedChangeListener(colorSelected1);
        mRadioGroupColor2.clearCheck();
        mRadioGroupColor2.setOnCheckedChangeListener(colorSelected2);

        mSeekBarPeriod.setRangeCount(4);
        mArrayListPeriodType = new ArrayList<>();
        mArrayListPeriodType.add("10일");
        mArrayListPeriodType.add("30일");
        mArrayListPeriodType.add("50일");
        mArrayListPeriodType.add("100일");
        mSeekBarPeriod.setStrings(mArrayListPeriodType);

        mButtonCreate.setOnClickListener(createHabit);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mButtonCreate.isEnabled()){
            mButtonCreate.setTextColor(getResources().getColor(R.color.white));
            mButtonCreate.requestLayout();
        }else{
            mButtonCreate.setTextColor(getResources().getColor(R.color.black));
            mButtonCreate.requestLayout();
        }
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String name = mEditTextName.getText().toString().trim();
            String startDate = mEditTextStartDate.getText().toString().trim();
            mButtonCreate.setEnabled(!(name.length()==0)&& !(startDate.length()==0));
        }
        public void afterTextChanged(Editable s) {
        }
    };

    private void updateLabel() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);

        Date today = new Date();
        mCurrentDate = sdf.parse(sdf.format(today));
        mHabitStartDate = sdf.parse(sdf.format(myCalendar.getTime()));
        int compare = mCurrentDate.compareTo(mHabitStartDate);

        if (compare <= 0) {
            mEditTextStartDate.setText(sdf.format(myCalendar.getTime()));
        } else {
            Toast.makeText(CreateHabitActivity.this, "날짜 다시 선택", Toast.LENGTH_SHORT).show();
            mEditTextStartDate.setText("");
        }
    }

    View.OnClickListener createHabit = v -> {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);

        mHabitName=mEditTextName.getText().toString();
        try {
            mHabitStartDate=sdf.parse(sdf.format(mHabitStartDate.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mHabitPeriod = mSeekBarPeriod.getProgress();
        Toast.makeText(CreateHabitActivity.this, mHabitName+mHabitStartDate+" "+Integer.toString(mHabitColor)+" "+Integer.toString(mHabitPeriod), Toast.LENGTH_SHORT).show();
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
