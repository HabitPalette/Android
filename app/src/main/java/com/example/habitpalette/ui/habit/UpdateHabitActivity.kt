package com.example.habitpalette.ui.habit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.habitpalette.R
import android.text.TextWatcher
import android.text.Editable
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.habitpalette.databinding.ActivityUpdateHabitBinding
import java.util.*

class UpdateHabitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateHabitBinding

    private var mHabitName: String? = null
    private val mHabitStartDate: Date? = null
    private val mHabitPeriod = 0
    private var mHabitColor = 0
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. View Binding 설정
        binding = ActivityUpdateHabitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etName.addTextChangedListener(textWatcher)

        // 2. btnUpdate에 click listener
        binding.btnUpdate.setOnClickListener {
            mHabitName = binding.etName.text.toString()
            Toast.makeText(
                this@UpdateHabitActivity,
                "$mHabitName$mHabitStartDate $mHabitColor $mHabitPeriod",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onResume() {
        super.onResume()
        if (binding.btnUpdate.isEnabled) {
            binding.btnUpdate.setTextColor(ContextCompat.getColor(this@UpdateHabitActivity, R.color.white))
            binding.btnUpdate.requestLayout()
        } else {
            binding.btnUpdate.setTextColor(ContextCompat.getColor(this@UpdateHabitActivity, R.color.gray))
            binding.btnUpdate.requestLayout()
        }
    }

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            val name = binding.etName.text.toString().trim { it <= ' ' }
            binding.btnUpdate.isEnabled = name.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable) {}
    }
}