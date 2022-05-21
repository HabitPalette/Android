package com.example.habitpalette.data.persistence

import androidx.room.*
import com.example.habitpalette.data.model.Habit
import com.example.habitpalette.data.model.HabitHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {
    @Query("SELECT * FROM habits WHERE is_completed=:is_completed_")
    fun getHabitList(is_completed_:Boolean): Flow<List<Habit>>

    @Query("SELECT * FROM habits WHERE id=:id_")
    fun getHabitItem(id_:Long): Flow<Habit>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createHabit(habit: Habit)

    @Query("UPDATE habits SET title=:title_, color=:color_ WHERE id=:id_")
    suspend fun updateHabit(title_: String, color_:Int, id_:Long)

    @Query("DELETE FROM habits WHERE id=:id_")
    suspend fun deleteHabit(id_: Long)

    @Query("DELETE FROM habits")
    suspend fun deleteAllHabits()

    @Query("SELECT * FROM habit_history WHERE habit_id=:habit_id_ AND id=:id_")
    suspend fun getHabitHistoryItem(habit_id_:Long, id_:Long): Flow<HabitHistory>

}