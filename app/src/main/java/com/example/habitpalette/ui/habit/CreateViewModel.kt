package com.example.habitpalette.ui.habit

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitpalette.data.model.Habit
import com.example.habitpalette.data.repository.HabitRepository
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class CreateViewModel(private val repository: HabitRepository):  ViewModel() {

    fun createHabitItem(newHabit: Habit) = viewModelScope.launch {
        repository.insert(newHabit)
    }
}