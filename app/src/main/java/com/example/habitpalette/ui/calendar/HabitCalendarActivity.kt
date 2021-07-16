package com.example.habitpalette.ui.calendar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.habitpalette.BR
import com.example.habitpalette.R
import com.example.habitpalette.databinding.CalendarListBinding
import java.util.*

class HabitCalendarActivity : AppCompatActivity() {

    private lateinit var binding: CalendarListBinding
    private lateinit var calendarAdapter: CalendarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_habit_calendar)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_habit_calendar)
        binding.setVariable(
            BR.model,
            ViewModelProvider(this)[CalendarListViewModel::class.java]
        )
        binding.lifecycleOwner = this

        binding.model!!.initCalendarList()

        val manager = StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL)
        calendarAdapter = CalendarAdapter()

        binding.pagerCalendar.layoutManager = manager
        binding.pagerCalendar.adapter =calendarAdapter

        observe()
    }

    private fun observe() {
        binding.model!!.mCalendarList.observe(this,
            { objects ->
                calendarAdapter.submitList(objects)
                if (binding.model?.mCenterPosition!! > 0) {
                    binding.pagerCalendar.scrollToPosition(binding.model?.mCenterPosition!!)
                }
            })
    }
}





