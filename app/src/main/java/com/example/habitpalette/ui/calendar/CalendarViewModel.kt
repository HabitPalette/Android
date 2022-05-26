package com.example.habitpalette.ui.calendar

import androidx.lifecycle.*
import com.example.habitpalette.data.model.Habit
import com.example.habitpalette.data.repository.HabitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    habitRepository: HabitRepository):  ViewModel() {

    val habitId: Long = savedStateHandle.get<Long>(HABIT_ID_SAVED_STATE_KEY)!!
    val habit : LiveData<Habit> = habitRepository.getHabitItem(habitId).asLiveData()

    companion object {
        private const val HABIT_ID_SAVED_STATE_KEY = "habitId"
    }
}