package com.example.habitpalette.ui.habit

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.habitpalette.data.model.Habit
import com.example.habitpalette.databinding.ItemCurrentHabitBinding

class CurrentHabitRecyclerAdapter(
    var currentList: List<Habit>,
    val onClickDetailButton: () -> Unit) :
    RecyclerView.Adapter<CurrentHabitRecyclerAdapter.CurrentHabitViewHolder>() {

    private lateinit var itemBinding: ItemCurrentHabitBinding

    inner class CurrentHabitViewHolder(private val itemBinding: ItemCurrentHabitBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(data: Habit) {
            itemBinding.tvTitle.text = data.title
            itemBinding.tvPeriod.text = data.duration.toString()
            itemBinding.pvCurrentScore.percent = 50f

            // item 클릭해서 CalendarActivity로 intent
            itemBinding.layoutCurrentItem.setOnClickListener {
                onClickDetailButton.invoke()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentHabitViewHolder {
        itemBinding = ItemCurrentHabitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrentHabitViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CurrentHabitViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int {
        return currentList.size
    }
}