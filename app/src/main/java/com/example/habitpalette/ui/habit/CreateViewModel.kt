package com.example.habitpalette.ui.habit

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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
class CreateViewModelFactory(private val repository: HabitRepository): ViewModelProvider.Factory{
    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CreateViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CreateViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}