package com.example.habitpalette.ui.calendar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.joda.time.DateTime

class CalendarAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {

    val START_POSITION = 0

    private var start: Long = DateTime().withDayOfMonth(1).withTimeAtStartOfDay().millis

    private var monthFragments: ArrayList<CalendarFragment> = ArrayList()

    override fun createFragment(position: Int): CalendarFragment {
        return monthFragments[position]
    }

    fun addFragment(fragment:CalendarFragment){
        monthFragments.add(fragment)
        notifyItemInserted(monthFragments.size-1)
    }

    override fun getItemId(position: Int): Long = DateTime(start).plusMonths((position - START_POSITION)).millis

    override fun getItemCount(): Int  = monthFragments.size
}