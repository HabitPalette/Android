package com.example.habitpalette.ui.habit

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.example.habitpalette.data.model.Habit
import com.example.habitpalette.databinding.ItemPastHabitBinding

class PastHabitRecyclerAdapter(
    var pastList: List<Habit>,
    val onClickDetailButton: () -> Unit) :
    RecyclerView.Adapter<PastHabitRecyclerAdapter.PastHabitViewHolder>() {

    private lateinit var itemBinding: ItemPastHabitBinding

    inner class PastHabitViewHolder(private val itemBinding: ItemPastHabitBinding): RecyclerView.ViewHolder(itemBinding.root){
        @SuppressLint("SetTextI18n")
        fun bind(data: Habit) {
            itemBinding.tvTitle.text = data.title
            itemBinding.tvScore.text = 3.2f.toString()
            itemBinding.tvPeriod.text = data.created_date.toString()+"~"+data.end_date.toString()
            itemBinding.pvPastScore.percent = 50f

            // item 클릭해서 CalendarActivity로 intent
            itemBinding.layoutPastItem.setOnClickListener {
                onClickDetailButton.invoke()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastHabitViewHolder {
        itemBinding = ItemPastHabitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PastHabitViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PastHabitViewHolder, position: Int) {
        holder.bind(pastList[position])
    }

    override fun getItemCount(): Int {
        return pastList.size
    }
}