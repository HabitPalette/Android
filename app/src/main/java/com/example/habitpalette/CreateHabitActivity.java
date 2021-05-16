package com.example.habitpalette;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
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
    private Button mButtonCreate;
    private int mSelectedColor;
    private Date mStartDate;
    private ArrayList<String> mArrayListPeriodType;
    private CreateHabitPeriodSeekBar mSeekBarPeriod;
    private Date mCurrentDate;

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

        mEditTextStartDate = (EditText) findViewById(R.id.edit_text_habit_start_date);
        mRadioGroupColor1 = (RadioGroup) findViewById(R.id.radio_group_habit_color_row1);
        mSeekBarPeriod = (CreateHabitPeriodSeekBar) findViewById(R.id.seek_bar_habit_period);


        mEditTextStartDate.setOnClickListener(v -> new DatePickerDialog(CreateHabitActivity.this, myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show());
        mEditTextStartDate.setInputType(InputType.TYPE_NULL);


        mRadioGroupColor1.clearCheck();
        mRadioGroupColor1.setOnCheckedChangeListener(colorSelected1);
        mRadioGroupColor2 = (RadioGroup) findViewById(R.id.radio_group_habit_color_row2);
        mRadioGroupColor2.clearCheck();
        mRadioGroupColor2.setOnCheckedChangeListener(colorSelected2);

        mSeekBarPeriod.setRangeCount(4);
        mArrayListPeriodType = new ArrayList<>();
        mArrayListPeriodType.add("10일");
        mArrayListPeriodType.add("30일");
        mArrayListPeriodType.add("50일");
        mArrayListPeriodType.add("100일");
        mSeekBarPeriod.setStrings(mArrayListPeriodType);
    }

    private void updateLabel() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);

        Date today = new Date();
        mCurrentDate = sdf.parse(sdf.format(today));
        mStartDate = sdf.parse(sdf.format(myCalendar.getTime()));
        int compare = mCurrentDate.compareTo(mStartDate);

        if(compare<=0){
            mEditTextStartDate.setText(sdf.format(myCalendar.getTime()));
        }
        else{
            Toast.makeText(CreateHabitActivity.this, "날짜 다시 선택", Toast.LENGTH_SHORT).show();
            mEditTextStartDate.setText("");
        }
    }

    RadioGroup.OnCheckedChangeListener colorSelected1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                mRadioGroupColor2.setOnCheckedChangeListener(null);
                mRadioGroupColor2.clearCheck();
                mRadioGroupColor2.setOnCheckedChangeListener(colorSelected2);
            }
            if (checkedId == R.id.button_habit_color_pink) {
                mSelectedColor = R.color.pink;
            } else if (checkedId == R.id.button_habit_color_red) {
                mSelectedColor = R.color.red;
            } else if (checkedId == R.id.button_habit_color_orange) {
                mSelectedColor = R.color.orange;
            } else if (checkedId == R.id.button_habit_color_yellow) {
                mSelectedColor = R.color.yellow;
            } else if (checkedId == R.id.button_habit_color_green) {
                mSelectedColor = R.color.green;
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
                mSelectedColor = R.color.light_green_blue;
            } else if (checkedId == R.id.button_habit_color_blue) {
                mSelectedColor = R.color.blue;
            } else if (checkedId == R.id.button_habit_color_light_blue_purple) {
                mSelectedColor = R.color.light_blue_purple;
            } else if (checkedId == R.id.button_habit_color_purple) {
                mSelectedColor = R.color.purple;
            } else if (checkedId == R.id.button_habit_color_gray) {
                mSelectedColor = R.color.gray;
            }
        }
    };
}
