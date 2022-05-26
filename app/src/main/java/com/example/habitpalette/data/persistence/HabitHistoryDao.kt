package com.example.habitpalette.data.persistence

import androidx.room.*
import com.example.habitpalette.data.model.HabitHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitHistoryDao {
    @Query("SELECT * FROM habit_history WHERE habit_id=:habit_id_ AND id=:id_")
    suspend fun getHabitHistoryItem(habit_id_:Long, id_:Long): Flow<List<HabitHistory>>

}