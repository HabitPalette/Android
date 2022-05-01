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
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*


class CreateHabitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateHabitBinding
    private val myCalendar: Calendar = Calendar.getInstance()

    @RequiresApi(Build.VERSION_CODES.O)
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

        // 2. btnBack intent 설정
        binding.btnBack.setOnClickListener { view: View? ->
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        // 3. etName에 textChangedListener 추가
        binding.etName.addTextChangedListener(textWatcher)

        // 4. etStartDate에 DatePickerDialog 추가
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

        // 5. sbPeriod에 날짜 list 추가
        binding.sbPeriod.setRangeCount(4)
        binding.sbPeriod.setStrings(arrayListOf("10일", "30일", "50일", "100일"))

        // 6. btnCreate에 setOnClickListener 추가

    }

    override fun onResume() {
        super.onResume()
        if (binding.btnCreate.isEnabled) {
            binding.btnCreate.setTextColor(ContextCompat.getColor(this@CreateHabitActivity, R.color.white))
            binding.btnCreate.requestLayout()
        } else {
            binding.btnCreate.setTextColor(ContextCompat.getColor(this@CreateHabitActivity, R.color.gray))
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
        var mHabitStartDate = sdf.parse(sdf.format(myCalendar.time))
        val compare = Date().compareTo(mHabitStartDate)
        if (compare <= 0) {
            binding.etStartDate.setText(sdf.format(myCalendar.time))
        } else {
            Toast.makeText(this@CreateHabitActivity, "날짜 다시 선택", Toast.LENGTH_SHORT).show()
            binding.etStartDate.setText("")
        }
    }

}