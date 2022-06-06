package com.example.habitpalette.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.habitpalette.data.model.Habit
import com.example.habitpalette.databinding.ItemCurrentHabitBinding
import com.example.habitpalette.ui.calendar.CalendarActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class CurrentHabitListAdapter(val context: Context):
    ListAdapter<Habit, CurrentHabitListAdapter.CurrentHabitViewHolder>(CurrentHabitComparator()){

    private lateinit var itemBinding: ItemCurrentHabitBinding

    inner class CurrentHabitViewHolder(private val itemBinding: ItemCurrentHabitBinding): RecyclerView.ViewHolder(itemBinding.root){
        private var habit:Habit? = null

        @SuppressLint("SetTextI18n")
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(data: Habit) {
            itemBinding.tvTitle.text = data.title
            itemBinding.tvPeriod.text = "습관 "+ChronoUnit.DAYS.between(LocalDate.parse(data.start_date.replace(".", ""), DateTimeFormatter.BASIC_ISO_DATE), LocalDate.now()).toString()+"일째"
            itemBinding.pvCurrentProgress.percent = ((data.score / 5.0) *100).toFloat()
            itemBinding.pvCurrentProgress.text = data.score.toString()
            itemBinding.pvCurrentProgress.fgColorStart = data.color
            itemBinding.pvCurrentProgress.fgColorEnd = data.color

            // 4. 각 habitItem 클릭시 CalendarActivity로 intent
            itemBinding.layoutCurrentItem.setOnClickListener {
                Intent(context, CalendarActivity::class.java).apply {
                    putExtra("habitId", data.id)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }
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