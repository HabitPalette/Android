package com.example.habitpalette.ui.home

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.habitpalette.data.model.Habit
import com.example.habitpalette.databinding.ItemPastHabitBinding

class PastHabitRecyclerAdapter:
    ListAdapter<Habit, PastHabitRecyclerAdapter.PastHabitViewHolder>(PastHabitComparator()){

    private lateinit var itemBinding: ItemPastHabitBinding

    inner class PastHabitViewHolder(private val itemBinding: ItemPastHabitBinding): RecyclerView.ViewHolder(itemBinding.root){
        private var habit:Habit? = null

        // item 클릭해서 CalendarActivity로 intent

        @SuppressLint("SetTextI18n")
        fun bind(data: Habit) {
            this.habit = data
            itemBinding.tvTitle.text = data.title
            itemBinding.tvScore.text = data.score.toString()
            itemBinding.tvPeriod.text = data.start_date.toString() + "~" + data.end_date.toString()
            itemBinding.pvPastProgress.percent = 50f
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastHabitViewHolder {
        itemBinding = ItemPastHabitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PastHabitViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PastHabitViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // Room을 사용할 경우 diffUtil로 notifyFataSetChanged 대체 가능
    class PastHabitComparator: DiffUtil.ItemCallback<Habit>(){
        override fun areItemsTheSame(oldItem: Habit, newItem: Habit): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Habit, newItem: Habit): Boolean {
            return oldItem.id == newItem.id
        }
    }
}