package com.example.habitpalette.ui.habit

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.habitpalette.R
import com.example.habitpalette.databinding.ActivityCreateHabitBinding
import com.example.habitpalette.ui.home.MainActivity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*


class CreateHabitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateHabitBinding

    private var mHabitName: String? = null
    private var mHabitStartDate: Date? = null
    private var mHabitPeriod = 0
    private var mHabitColor = 0
    private var mArrayListPeriodType: ArrayList<String>? = null
    @RequiresApi(Build.VERSION_CODES.O)
    private val mCurrentDate = LocalDateTime.now()
    private val myCalendar: Calendar = Calendar.getInstance()

    var myDatePicker =
        OnDateSetListener { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
            myCalendar[Calendar.YEAR] = year
            myCalendar[Calendar.MONTH] = month
            myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            try {
                updateLabel()
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1. View Binding 설정
        binding = ActivityCreateHabitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { view: View? ->
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        binding.etName.addTextChangedListener(textWatcher)

        binding.etStartDate.setOnClickListener { v: View? ->
            DatePickerDialog(
                this@CreateHabitActivity,
                myDatePicker,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }
        binding.etStartDate.inputType = InputType.TYPE_NULL
        binding.etStartDate.addTextChangedListener(textWatcher)

        mHabitColor = R.color.pink
        binding.rgColorTop.setOnCheckedChangeListener(colorSelected1)
        binding.rgColorTop.clearCheck()

//        binding.rgColorBottom.setOnCheckedChangeListener(colorSelected2)
        binding.sbPeriod.setRangeCount(4)
        mArrayListPeriodType = ArrayList()
        mArrayListPeriodType!!.add("10일")
        mArrayListPeriodType!!.add("30일")
        mArrayListPeriodType!!.add("50일")
        mArrayListPeriodType!!.add("100일")
        binding.sbPeriod.setStrings(mArrayListPeriodType)
        binding.btnCreate.setOnClickListener(createHabit)
    }

    override fun onResume() {
        super.onResume()
        if (binding.btnCreate.isEnabled) {
            binding.btnCreate.setTextColor(ContextCompat.getColor(this@CreateHabitActivity, R.color.white))
            binding.btnCreate.requestLayout()
        } else {
            binding.btnCreate.setTextColor(ContextCompat.getColor(this@CreateHabitActivity, R.color.black))
            binding.btnCreate.requestLayout()
        }
    }

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val name = binding.etName.text.toString().trim { it <= ' ' }
            val startDate = binding.etStartDate.text.toString().trim { it <= ' ' }
            binding.btnCreate.isEnabled = name.isNotEmpty() && startDate.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable) {}
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(ParseException::class)
    private fun updateLabel() {
        val sdf = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
        mHabitStartDate = sdf.parse(sdf.format(myCalendar.time))
        val compare = mCurrentDate.compareTo(mHabitStartDate)
        if (compare <= 0) {
            binding.etStartDate.setText(sdf.format(myCalendar.time))
        } else {
            Toast.makeText(this@CreateHabitActivity, "날짜 다시 선택", Toast.LENGTH_SHORT).show()
            binding.etStartDate.setText("")
        }
    }

    var createHabit = View.OnClickListener { v: View? ->
        val sdf = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
        mHabitName = binding.etName.text.toString()
        try {
            mHabitStartDate = sdf.parse(sdf.format(mHabitStartDate!!.time))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        mHabitPeriod = binding.sbPeriod.progress
        Toast.makeText(
            this@CreateHabitActivity,
            mHabitName + mHabitStartDate + " " + Integer.toString(mHabitColor) + " " + Integer.toString(
                mHabitPeriod
            ),
            Toast.LENGTH_SHORT
        ).show()
    }
    var colorSelected1 = RadioGroup.OnCheckedChangeListener { group, checkedId ->
        if (checkedId == R.id.button_habit_color_pink) {
            mHabitColor = R.color.pink
        } else if (checkedId == R.id.button_habit_color_red) {
            mHabitColor = R.color.red
        } else if (checkedId == R.id.button_habit_color_orange) {
            mHabitColor = R.color.orange
        } else if (checkedId == R.id.button_habit_color_yellow) {
            mHabitColor = R.color.yellow
        } else if (checkedId == R.id.button_habit_color_green) {
            mHabitColor = R.color.green
        }
    }
}