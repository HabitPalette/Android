package com.example.habitpalette.ui.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.habitpalette.R
import com.example.habitpalette.databinding.CalendarHeaderBinding
import com.example.habitpalette.databinding.DayItemBinding
import com.example.habitpalette.databinding.EmptyDayBinding
import com.google.gson.Gson
import java.util.*

class CalendarAdapter() :
    ListAdapter<Any, RecyclerView.ViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any) = oldItem == newItem

            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                val gson = Gson()
                return gson.toJson(oldItem).equals(gson.toJson(newItem))
            }

        }
    }

    private val HEADER_TYPE = 0
    private val EMPTY_TYPE = 1
    private val DAY_TYPE = 2

    override fun getItemViewType(position: Int): Int { //뷰타입 나누기
        val item = getItem(position)
        return if (item is Long) {
            HEADER_TYPE //날짜 타입
        } else if (item is String) {
            EMPTY_TYPE // 비어있는 일자 타입
        } else {
            DAY_TYPE // 일자 타입
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == HEADER_TYPE) {
            val binding: CalendarHeaderBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_calendar_header,
                parent,
                false
            )
            val params = binding.root.layoutParams as StaggeredGridLayoutManager.LayoutParams
            params.isFullSpan = true //Span을 하나로 통합하기
            binding.root.layoutParams = params
            return HeaderViewHolder(binding)
        } else if (viewType == EMPTY_TYPE) {
            val binding: EmptyDayBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_calendar_empty_day,
                parent,
                false
            )
            return EmptyViewHolder(binding)
        }
        val binding: DayItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_calendar_day,
                parent,
                false
            )
        return DayViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        var viewType = getItemViewType(position)
        if (viewType == HEADER_TYPE) {
            val holder = viewHolder as HeaderViewHolder
            val item = getItem(position)
            val model = CalendarHeaderViewModel()
            if (item is Long) {
                model.setHeaderDate(item)
            }
            holder.setViewModel(model)
        } else if (viewType == EMPTY_TYPE) {
            val holder = viewHolder as EmptyViewHolder
            val model = EmptyViewModel()
            holder.setViewModel(model)

        } else if (viewType == DAY_TYPE) {
            val holder = viewHolder as DayViewHolder
            val item = getItem(position)
            val model = CalendarViewModel()
            if (item is Calendar) {
                model.setCalendar(item)
            }
            holder.setViewModel(model)
        }
    }


    private class HeaderViewHolder(  //날짜 타입 ViewHolder
        private val binding: CalendarHeaderBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
         fun setViewModel(model: CalendarHeaderViewModel) {
            binding.model = model
            binding.executePendingBindings()
        }
    }

    private class EmptyViewHolder (  // 비어있는 요일 타입 ViewHolder
        private val binding: EmptyDayBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
         fun setViewModel(model: EmptyViewModel) {
            binding.model = model
            binding.executePendingBindings()
        }
    }


    private class DayViewHolder (  // 요일 타입 ViewHolder
        private val binding: DayItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
         fun setViewModel(model: CalendarViewModel) {
            binding.model = model
            binding.executePendingBindings()
        }
    }

}