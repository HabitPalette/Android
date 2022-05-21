package com.example.habitpalette.ui.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.habitpalette.data.model.Habit
import com.example.habitpalette.databinding.ItemCurrentHabitBinding
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class CurrentHabitRecyclerAdapter:
    ListAdapter<Habit, CurrentHabitRecyclerAdapter.CurrentHabitViewHolder>(CurrentHabitComparator()){

    private lateinit var itemBinding: ItemCurrentHabitBinding

    inner class CurrentHabitViewHolder(private val itemBinding: ItemCurrentHabitBinding): RecyclerView.ViewHolder(itemBinding.root){
        private var habit:Habit? = null

        // item 클릭해서 CalendarActivity로 intent

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(data: Habit) {
            itemBinding.tvTitle.text = data.title
            itemBinding.tvPeriod.text = ChronoUnit.DAYS.between(data.start_date, LocalDate.now()).toString()+"일째"
            itemBinding.pvCurrentProgress.percent = 50f
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentHabitViewHolder {
        itemBinding = ItemCurrentHabitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrentHabitViewHolder(itemBinding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CurrentHabitViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // Room을 사용할 경우 diffUtil로 notifyFataSetChanged 대체 가능
    class CurrentHabitComparator: DiffUtil.ItemCallback<Habit>(){
        override fun areItemsTheSame(oldItem: Habit, newItem: Habit): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Habit, newItem: Habit): Boolean {
            return oldItem.id == newItem.id
        }
    }
}