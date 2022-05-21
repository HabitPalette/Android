package com.example.habitpalette.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.habitpalette.databinding.FragmentCalendarBinding
import com.example.habitpalette.utils.CalendarUtil.Companion.getMonthList
import org.joda.time.DateTime

class CalendarFragment : Fragment(){

    private lateinit var binding: FragmentCalendarBinding
    private var millis: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            millis = it.getLong(MILLIS)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 1. View Binding 설정
        binding = FragmentCalendarBinding.inflate(inflater, container, false)

        // 2. event


        // 3. calendarView 초기화
        binding.rvCalendar.initCalendar(DateTime(millis), getMonthList(DateTime(millis)))

        // 4. return Fragment Layout View
        return binding.root
    }

    companion object {

        private const val MILLIS = "MILLIS"

        fun newInstance(millis: Long) = CalendarFragment().apply {
            arguments = Bundle().apply {
                putLong(MILLIS, millis)
            }
        }
    }
}