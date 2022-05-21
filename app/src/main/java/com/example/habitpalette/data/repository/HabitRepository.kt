package com.example.habitpalette.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.habitpalette.data.model.Habit
import com.example.habitpalette.data.persistence.HabitDao
import kotlinx.coroutines.flow.Flow

class HabitRepository(private val habitDao: HabitDao) {
    val pastList: Flow<List<Habit>> = habitDao.getHabitList(true)
    val presentList: Flow<List<Habit>> = habitDao.getHabitList(false)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(habit: Habit) {
        habitDao.createHabit(habit)
    }
    suspend fun getHabitItemById(id:Long): Habit {
        return habitDao.getHabitItem(id)
    }
    suspend fun updateHabitItem(title: String, color:Int, id:Long){
        habitDao.updateHabit(title, color, id)
    }
    suspend fun deleteHabitItem(id:Long){
        habitDao.deleteHabit(id)
    }
}