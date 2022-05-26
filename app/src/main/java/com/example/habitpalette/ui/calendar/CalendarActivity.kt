package com.example.habitpalette.ui.calendar

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.habitpalette.databinding.ActivityCalendarBinding
import dagger.hilt.android.AndroidEntryPoint
import org.joda.time.DateTime


@AndroidEntryPoint
class CalendarActivity : AppCompatActivity(){
    private lateinit var binding: ActivityCalendarBinding
    private lateinit var adapter: CalendarAdapter

    // 2. ViewModel 설정
    private val calendarViewModel : CalendarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. View Binding 설정
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. habit에 Observe 설정 => 그래야 null이 아님!!
        calendarViewModel.habit.observe(this, Observer {
            binding.tvHabitTitle.text = calendarViewModel.habit.value!!.title
        })

        // 3. PagerAdapter 설정
        adapter = CalendarAdapter(this)
        binding.vpCalendar.adapter = adapter
        binding.vpCalendar.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.vpCalendar.setCurrentItem(CalendarAdapter.START_POSITION, false)

        // 4. 오늘자 년/월 표시 (Int.Max_VALUE /2 대체 필요)
        binding.tvYearMonth.text = DateTime(adapter.getItemId( Int.MAX_VALUE / 2)).toString("yyyy-MM")

        // 5. btnBeforeMonth에 setOnClickListener로 버튼으로도 viewpager 이동, text 변경
        binding.btnBeforeMonth.setOnClickListener {
            val current = binding.vpCalendar.currentItem
            if (current > 0){
                binding.vpCalendar.setCurrentItem(current-1, false)
                binding.tvYearMonth.text = DateTime(adapter.getItemId(current-1)).toString("yyyy-MM")
            }
        }

        // 6. btnAfterMonth에 setOnClickListener로 버튼으로도 viewpager 이동, text 변경
        binding.btnAfterMonth.setOnClickListener {
            val current = binding.vpCalendar.currentItem
            if (current < adapter.itemCount){
                binding.vpCalendar.setCurrentItem(current+1, false)
                binding.tvYearMonth.text = DateTime(adapter.getItemId(current+1)).toString("yyyy-MM")
            }
        }

        // 7. viewpager에 listener로 text 변경
        binding.vpCalendar.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.tvYearMonth.text = DateTime(adapter.getItemId(position)).toString("yyyy-MM")
            }
        })
    }
}

