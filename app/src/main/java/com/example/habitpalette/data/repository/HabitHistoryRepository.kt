package com.example.habitpalette.data.repository

import androidx.annotation.WorkerThread
import com.example.habitpalette.data.model.HabitHistory
import com.example.habitpalette.data.persistence.HabitHistoryDao
import kotlinx.coroutines.flow.Flow

class HabitHistoryRepository(private val habitHistory: HabitHistoryDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getHabitHistoryItem(habit_id:Long, id:Long): Flow<List<HabitHistory>> {
        return habitHistory.getHabitHistoryItem(habit_id, id)
    }
}