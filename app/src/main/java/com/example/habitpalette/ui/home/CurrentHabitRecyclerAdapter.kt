package com.example.habitpalette.ui.home

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.RequiresApi
import com.example.habitpalette.data.model.Habit
import com.example.habitpalette.databinding.ItemCurrentHabitBinding
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class CurrentHabitRecyclerAdapter(
    val context: Context,
    var presentList: List<Habit>,
    val onClickDetailButton: () -> Unit) :
    RecyclerView.Adapter<CurrentHabitRecyclerAdapter.CurrentHabitViewHolder>() {

    private lateinit var itemBinding: ItemCurrentHabitBinding

    inner class CurrentHabitViewHolder(private val itemBinding: ItemCurrentHabitBinding): RecyclerView.ViewHolder(itemBinding.root){
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(data: Habit) {
//            if(adapterPosition==0 || adapterPosition == presentList.size-1){
//                itemBinding.layoutCurrentItem.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
//
//                val displayMetrics = context.resources.displayMetrics
//                val screenWidth = displayMetrics.widthPixels
//                var mLayoutParam : RecyclerView.LayoutParams =  itemBinding.layoutCurrentItem.layoutParams as RecyclerView.LayoutParams
//                if(adapterPosition == 0)
//                    mLayoutParam.leftMargin = (screenWidth - itemBinding.layoutCurrentItem.measuredWidthAndState)/2
//                else
//                    mLayoutParam.rightMargin = (screenWidth - itemBinding.layoutCurrentItem.measuredWidthAndState)/2
//            }

            itemBinding.tvTitle.text = data.title
            itemBinding.tvPeriod.text = ChronoUnit.DAYS.between(data.start_date, LocalDate.now()).toString()+"일째"
            itemBinding.pvCurrentProgress.percent = 50f

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CurrentHabitViewHolder, position: Int) {
        holder.bind(presentList[position])
        Log.d("CURRENT ADAPTER", presentList.toString())
    }

    override fun getItemCount(): Int {
        return presentList.size
    }

    fun setData(presentHabitList: List<Habit>){
        presentList = presentHabitList
        notifyDataSetChanged()
    }
}