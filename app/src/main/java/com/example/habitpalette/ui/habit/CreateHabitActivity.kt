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
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.habitpalette.HabitPaletteApplication
import com.example.habitpalette.R
import com.example.habitpalette.data.model.Habit
import com.example.habitpalette.databinding.ActivityCreateHabitBinding
import com.example.habitpalette.ui.home.MainActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class CreateHabitActivity : AppCompatActivity() {

    // 1. View Model 설정
    private val viewModel: CreateViewModel by viewModels{
        CreateViewModelFactory((application as HabitPaletteApplication).repository)
    }
    private lateinit var binding: ActivityCreateHabitBinding

    @RequiresApi(Build.VERSION_CODES.O)
    var mStartDate: LocalDate = LocalDate.MIN

    @RequiresApi(Build.VERSION_CODES.O)
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. View Binding 설정
        binding = ActivityCreateHabitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. btnBack intent 설정
        binding.btnBack.setOnClickListener { view: View? ->
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        // 4. etName에 textChangedListener 추가
        binding.etName.addTextChangedListener(textWatcher)

        // 5. etStartDate에 DatePickerDialog 추가
        binding.etStartDate.setOnClickListener {
            val cal = Calendar.getInstance()

            val dateSetListener = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                mStartDate = LocalDate.parse("${year}-${if((monthOfYear+1).toString().length!=1) {(monthOfYear+1).toString()} else {"0"+((monthOfYear+1).toString())}}-${if(dayOfMonth.toString().length!=1) {dayOfMonth.toString()} else {
                    "0$dayOfMonth"
                }}", DateTimeFormatter.ISO_DATE)
                val nowDate = LocalDate.now()
                if(nowDate.isBefore(mStartDate) || nowDate.isEqual(mStartDate)){
                    binding.etStartDate.setText(mStartDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                }else{
                    Toast.makeText(this@CreateHabitActivity, "날짜 다시 선택", Toast.LENGTH_SHORT).show()
                    binding.etStartDate.setText("")
                }
            }
            DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
        }
        binding.etStartDate.inputType = InputType.TYPE_CLASS_DATETIME
        binding.etStartDate.addTextChangedListener(MaskWatcher.buildCpf())

        // 6. sbPeriod에 날짜 list 추가
        binding.sbPeriod.setRangeCount(4)
        binding.sbPeriod.setStrings(arrayListOf("10일", "30일", "50일", "100일"))

        // 7. 색상 선택
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        // 1. btnCreate에 setOnClickListener 추가
        binding.btnCreate.setOnClickListener{
            val title = binding.etName.text.toString()
            val startDate = LocalDate.parse(binding.etStartDate.text.toString().replace(".", ""), DateTimeFormatter.BASIC_ISO_DATE)
            val duration = returnDuration(binding.sbPeriod.progress)
            val endDate = startDate.plusDays(duration.toLong())
            val isCompleted = false
            val color = ContextCompat.getColor(this, R.color.pink)
            val usersId = 0L
            val score = 0f
            val startDateText = binding.etStartDate.text.toString()
            val endDateText = endDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))

            val habit = Habit(null, title,startDateText, endDateText,duration,isCompleted,color, usersId,score)
            viewModel.createHabitItem(habit)

            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
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

    private fun returnDuration(position: Int): Int{
        val dayList = listOf(10,30,50,100)
        return dayList[position]
    }

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val name = binding.etName.text.toString().trim { it <= ' ' }
            binding.btnCreate.isEnabled = name.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable) {}
    }
}

class MaskWatcher(private val mask: String) : TextWatcher {
    private var isRunning = false
    private var isDeleting = false

    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
        isDeleting = count > after
    }

    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable) {
        if (isRunning || isDeleting) {
            return
        }
        isRunning = true
        val editableLength = editable.length
        if (editableLength < mask.length) {
            if (mask[editableLength] != '#') {
                editable.append(mask[editableLength])
            } else if (mask[editableLength - 1] != '#') {
                editable.insert(editableLength - 1, mask, editableLength - 1, editableLength)
            }
        }
        isRunning = false
    }

    companion object {
        fun buildCpf(): MaskWatcher {
            return MaskWatcher("####.##.##")
        }
    }
}