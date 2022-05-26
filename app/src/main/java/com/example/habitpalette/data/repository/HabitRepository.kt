package com.example.habitpalette.data.repository

import androidx.annotation.WorkerThread
import com.example.habitpalette.data.model.Habit
import com.example.habitpalette.data.persistence.HabitDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HabitRepository @Inject constructor(private val habitDao: HabitDao) {
    val pastList: Flow<List<Habit>> = habitDao.getHabitList(true)
    val presentList: Flow<List<Habit>> = habitDao.getHabitList(false)
    fun getHabitItem(habitId:Long): Flow<Habit> = habitDao.getHabitItem(habitId)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(habit: Habit) {
        habitDao.createHabit(habit)
    }
    suspend fun updateHabitItem(title: String, color:Int, id:Long){
        habitDao.updateHabit(title, color, id)
    }
    suspend fun deleteHabitItem(id:Long){
        habitDao.deleteHabit(id)
    }
}