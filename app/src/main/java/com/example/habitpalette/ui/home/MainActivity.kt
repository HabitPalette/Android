package com.example.habitpalette.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.habitpalette.data.model.Habit
import com.example.habitpalette.databinding.ActivityMainBinding
import com.example.habitpalette.ui.calendar.CalendarActivity
import com.example.habitpalette.ui.habit.CreateHabitActivity
import com.example.habitpalette.ui.habit.CurrentHabitRecyclerAdapter
import com.example.habitpalette.ui.habit.PastHabitRecyclerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mCurrentHabitAdapter : CurrentHabitRecyclerAdapter
    private lateinit var mPastHabitAdapter : PastHabitRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. View Binding 설정
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. btnCreateHabit 눌러서 CreateHabitActivity로 intent
        binding.btnCreateHabit.setOnClickListener {
            Intent(this, CreateHabitActivity::class.java).also{
                startActivity(it)
            }
        }

        // 3. mPastHabitAdapter 설정
        var pastList = emptyList<Habit>()
        mPastHabitAdapter = PastHabitRecyclerAdapter(
            pastList,
            onClickDetailButton = {
                Intent(this, CalendarActivity::class.java).also{
                startActivity(it)}
            }
        )
        mPastHabitAdapter.setHasStableIds(true)
        binding.rvPastHabit.adapter = mPastHabitAdapter
        binding.rvPastHabit.layoutManager = LinearLayoutManager(this)

        // 4. mCurrentHabitAdapter 설정
        var currentList = emptyList<Habit>()
        mCurrentHabitAdapter = CurrentHabitRecyclerAdapter(
            currentList,
            onClickDetailButton={
                Intent(this, CalendarActivity::class.java).also{
                    startActivity(it)}
            }
        )
        mCurrentHabitAdapter.setHasStableIds(true)
        binding.rvCurrentHabit.adapter = mCurrentHabitAdapter
        binding.rvCurrentHabit.layoutManager = LinearLayoutManager(this)

    }
}