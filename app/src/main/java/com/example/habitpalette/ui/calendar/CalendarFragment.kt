package com.example.habitpalette.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.habitpalette.R
import com.example.habitpalette.data.model.SimpleEvent
import com.example.habitpalette.databinding.FragmentCalendarBinding
import com.example.habitpalette.utils.CalendarUtil.Companion.dateStringFromFormat
import com.example.habitpalette.utils.CalendarUtil.Companion.getMonthList
import dagger.hilt.android.AndroidEntryPoint
import org.joda.time.DateTime
import java.util.*

@AndroidEntryPoint
class CalendarFragment : Fragment(){

    private lateinit var binding: FragmentCalendarBinding
    private val eventMap: HashMap<Int, SimpleEvent> = HashMap()
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

        // 2. event 예시로 생성
        for (i in 0..30 step 3) {
            val eventCal = Calendar.getInstance()
            eventCal.add(Calendar.DATE, i + 3)
            val eventDate: Int = (dateStringFromFormat(date = eventCal.time, format = "yyyyMMdd") ?: "0").toInt()
            eventMap[eventDate] = SimpleEvent(
                date = eventCal.time,
                title = "Event #$i",
                color = ContextCompat.getColor((activity as CalendarActivity), R.color.light_blue_purple),
                progress = i * 3
            )
        }

        // 3. calendarView 초기화
        binding.rvCalendar.initCalendar(DateTime(millis), getMonthList(DateTime(millis)), eventMap)

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