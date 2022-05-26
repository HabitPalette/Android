package com.example.habitpalette.ui.habit

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitpalette.data.model.Habit
import com.example.habitpalette.data.repository.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class CreateViewModel @Inject constructor(val repository: HabitRepository):  ViewModel() {

    fun createHabitItem(newHabit: Habit) = viewModelScope.launch {
        repository.insert(newHabit)
    }
}